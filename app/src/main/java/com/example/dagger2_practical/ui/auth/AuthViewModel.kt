package com.example.dagger2_practical.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.dagger2_practical.network.auth.AuthApi
import javax.inject.Inject

class AuthViewModel @Inject constructor(authApi: AuthApi) : ViewModel() {

    companion object {
        private const val TAG = "AuthViewModel"
    }

    init {
        Log.d(TAG, "AuthViewModel: viewmodel is injected")
        if (authApi == null) {
            Log.d(TAG, "authApi is null")
        } else {
            Log.d(TAG, "authApi is not null")
        }
    }


}