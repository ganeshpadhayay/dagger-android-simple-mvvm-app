package com.example.dagger2_practical

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.example.dagger2_practical.models.User
import com.example.dagger2_practical.ui.auth.AuthResource
import javax.inject.Inject
import javax.inject.Singleton

@Singleton class SessionManager @Inject constructor() {

    companion object {
        private const val TAG = "SessionManager"
    }

    // data
    private val cachedUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData<AuthResource<User>>()

    fun authenticateWithId(source: LiveData<AuthResource<User>>) {
        cachedUser.value = AuthResource.loading(null as User?)
        cachedUser.addSource(source) { userAuthResource ->
            cachedUser.value = userAuthResource
            cachedUser.removeSource(source)
            if (userAuthResource.status == AuthResource.AuthStatus.ERROR) {
                cachedUser.value = AuthResource.logout()
            }
        }
    }

    fun logOut() {
        Log.d(TAG, "logOut: logging out...")
        cachedUser.value = AuthResource.logout()
    }

    val authUser: MediatorLiveData<AuthResource<User>>
        get() = cachedUser

}