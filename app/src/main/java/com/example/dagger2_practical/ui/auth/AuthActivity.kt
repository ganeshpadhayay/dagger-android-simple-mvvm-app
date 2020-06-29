package com.example.dagger2_practical.ui.auth

import android.content.Intent
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.RequestManager
import com.example.dagger2_practical.R
import com.example.dagger2_practical.models.User
import com.example.dagger2_practical.ui.auth.AuthResource.AuthStatus
import com.example.dagger2_practical.ui.main.MainActivity
import com.example.dagger2_practical.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject
import javax.inject.Named


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

    @Inject
    @Named("app-user")
    lateinit var appUser: User

    @Inject
    @Named("auth-user")
    lateinit var authUser: User


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)
        authViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(AuthViewModel::class.java)
        setLogo()
        subscribeObservers()

        login_button.setOnClickListener {
            attemptLogin()
        }

        //small demo to distinguish between scopes
        Log.d(TAG, "onCreate: app user: $appUser")
        Log.d(TAG, "onCreate: auth user: $authUser")
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(user_id_input.text.toString())) {
            return
        }
        authViewModel.authenticateWithId(Integer.parseInt(user_id_input.text.toString()))
    }

    private fun subscribeObservers() {
        authViewModel.observeAuthState().observe(this, Observer { userAuthResource ->
            if (userAuthResource != null) {
                when (userAuthResource.status) {
                    AuthStatus.LOADING -> {
                        showProgressBar(true)
                    }
                    AuthStatus.AUTHENTICATED -> {
                        showProgressBar(false)
                        Log.d(TAG, "onChanged: LOGIN SUCCESS: " + userAuthResource.data?.email)
                        onLoginSuccess(userAuthResource.data)
                        finish()
                    }
                    AuthStatus.ERROR -> {
                        Log.e(TAG, "onChanged: " + userAuthResource.message)
                        showProgressBar(false)
                        Toast.makeText(this@AuthActivity, """${userAuthResource.message}Did you enter a number between 0 and 10?""".trimIndent(), Toast.LENGTH_SHORT).show()
                    }
                    AuthStatus.NOT_AUTHENTICATED -> {
                        showProgressBar(false)
                    }
                }
            }
        })
    }

    private fun onLoginSuccess(data: User?) {
        startActivity(Intent(this, MainActivity::class.java))
    }

    private fun setLogo() {
        requestManager.load(logo).into(login_logo)
    }

    private fun showProgressBar(isVisible: Boolean) {
        if (isVisible) {
            progress_bar.visibility = View.VISIBLE
        } else {
            progress_bar.visibility = View.GONE
        }
    }
}