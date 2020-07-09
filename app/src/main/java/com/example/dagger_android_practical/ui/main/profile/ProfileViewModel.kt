package com.example.dagger_android_practical.ui.main.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.dagger_android_practical.SessionManager
import com.example.dagger_android_practical.models.User
import com.example.dagger_android_practical.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(var sessionManager: SessionManager) : ViewModel() {

    companion object {
        private const val TAG = "ProfileViewModel"
    }

    fun getAuthenticatedUser(): LiveData<AuthResource<User>> {
        return sessionManager.authUser
    }

}