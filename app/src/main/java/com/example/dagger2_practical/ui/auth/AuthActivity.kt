package com.example.dagger2_practical.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.dagger2_practical.R
import com.example.dagger2_practical.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    companion object {
        private const val TAG = "AuthActivity"
    }

    private lateinit var authViewModel: AuthViewModel

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel::class.java)
        setLogo()
        subscribeObservers()

        login_button.setOnClickListener {
            attemptLogin()
        }
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(user_id_input.text.toString())) {
            return
        }
        authViewModel.authenticateWithId(Integer.parseInt(user_id_input.text.toString()))
    }

    private fun subscribeObservers() {
        authViewModel.observeUser().observe(this, Observer {
            if (it != null) {
                Log.d(TAG, "subscribeObservers: ${it.email}")
            }
        })
    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }
}