package com.example.dagger2_practical.ui.auth

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.dagger2_practical.models.User
import com.example.dagger2_practical.network.auth.AuthApi
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(var authApi: AuthApi) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    private val authUser: MediatorLiveData<AuthResource<User>> = MediatorLiveData()

    fun authenticateWithId(userId: Int) {
        authUser.value = AuthResource.loading(null)

        val source = LiveDataReactiveStreams.fromPublisher(authApi.getUser(userId).onErrorReturn {
            val errorUser = User()
            errorUser.id = -1
            errorUser
        }.map {
            if (it.id === -1) {
                AuthResource.error("Could not authenticate", null)
            } else AuthResource.authenticated(it)
        }.subscribeOn(Schedulers.io()))

        authUser.addSource(source) {
            authUser.value = it as AuthResource<User>?
            authUser.removeSource(source)
        }
    }

    fun observeUser(): LiveData<AuthResource<User>> {
        return authUser
    }
}