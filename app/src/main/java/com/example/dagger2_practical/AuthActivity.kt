package com.example.dagger2_practical

import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var text: String

    @JvmField
    @Inject
    var isAppNull: Boolean = false

    companion object {
        private const val TAG = "AuthActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(TAG, "onCreate: $text")
        Log.d(TAG, "onCreate: $isAppNull")
    }


}