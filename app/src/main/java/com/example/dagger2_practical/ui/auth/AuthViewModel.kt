package com.example.dagger2_practical.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dagger2_practical.models.User
import com.example.dagger2_practical.network.auth.AuthApi
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG, "AuthViewModel: view model is injected")

        if (authApi == null) {
            Log.d(TAG, "authApi is null")
        } else {
            Log.d(TAG, "authApi is not null")
        }

        authApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(object : Observer<User> {
                override fun onComplete() {
                }

                override fun onSubscribe(d: Disposable) {
                }

                override fun onNext(user: User) {
                    Log.d(TAG, "onNext: ${user.email}")
                }

                override fun onError(e: Throwable) {
                    Log.d(TAG, "onNext: ${e.message}")
                }
            })
    }
}