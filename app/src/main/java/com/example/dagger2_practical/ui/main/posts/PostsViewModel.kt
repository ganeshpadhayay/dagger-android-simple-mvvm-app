package com.example.dagger2_practical.ui.main.posts

import androidx.lifecycle.ViewModel
import com.example.dagger2_practical.SessionManager
import com.example.dagger2_practical.network.main.MainApi
import javax.inject.Inject

class PostsViewModel @Inject constructor(var mainApi: MainApi, var sessionManager: SessionManager) : ViewModel() {

    companion object {
        private const val TAG = "PostsViewModel"
    }


}