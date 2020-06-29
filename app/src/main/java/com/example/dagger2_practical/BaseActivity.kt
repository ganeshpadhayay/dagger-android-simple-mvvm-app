package com.example.dagger2_practical

import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.dagger2_practical.ui.auth.AuthActivity
import com.example.dagger2_practical.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {

    companion object {
        private const val TAG = "BaseActivity"
    }

    @Inject lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.authUser.observe(this, Observer { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthResource.AuthStatus.LOADING -> {
                    }
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                    }
                    AuthResource.AuthStatus.ERROR -> {
                    }
                    AuthResource.AuthStatus.NOT_AUTHENTICATED -> {
                        navLoginScreen()
                        finish()
                    }
                }
            }
        })
    }

    private fun navLoginScreen() {
        startActivity(Intent(this, AuthActivity::class.java))
    }
}