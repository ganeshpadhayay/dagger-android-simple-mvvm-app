package com.example.dagger_android_practical.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.ViewModel
import com.example.dagger_android_practical.SessionManager
import com.example.dagger_android_practical.models.User
import com.example.dagger_android_practical.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(var authApi: AuthApi, var sessionManager: SessionManager) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    fun authenticateWithId(userId: Int) {
        Log.d(TAG, "authenticateWithId: attempting to login")
        sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams.fromPublisher(authApi.getUser(userId).subscribeOn(Schedulers.io()).onErrorReturn {
            val errorUser = User()
            errorUser.id = -1
            errorUser
        }.map {
            if (it.id === -1) {
                AuthResource.error("Could not authenticate", null)
            } else AuthResource.authenticated(it)
        }) as LiveData<AuthResource<User>>
    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.authUser
    }
}