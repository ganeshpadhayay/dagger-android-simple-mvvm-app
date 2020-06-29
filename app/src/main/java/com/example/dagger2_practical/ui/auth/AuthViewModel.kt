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

    private val authUser: MediatorLiveData<User> = MediatorLiveData()

    fun authenticateWithId(userId: Int) {
        val source: LiveData<User> = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId).subscribeOn(
                Schedulers.io()
            )
        )
        authUser.addSource(source) {
            authUser.value = it
            authUser.removeSource(source)
        }
    }

    fun observeUser(): LiveData<User> {
        return authUser
    }
}