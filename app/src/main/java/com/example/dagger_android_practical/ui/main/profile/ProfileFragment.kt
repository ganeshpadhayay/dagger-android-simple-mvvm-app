package com.example.dagger_android_practical.ui.main.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dagger_android_practical.R
import com.example.dagger_android_practical.models.User
import com.example.dagger_android_practical.ui.auth.AuthResource
import com.example.dagger_android_practical.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {

    companion object {
        private const val TAG = "ProfileFragment"
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var profileViewModel: ProfileViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        profileViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        //this is required due to the life cycles issues of fragments
        profileViewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        profileViewModel.getAuthenticatedUser().observe(viewLifecycleOwner, Observer {
            if (it != null) {
                when (it.status) {
                    AuthResource.AuthStatus.AUTHENTICATED -> {
                        setUserDetails(it.data)
                    }
                    AuthResource.AuthStatus.ERROR -> {
                        setErrorDetails(it.message)
                    }
                }
            }
        })
    }

    private fun setUserDetails(data: User?) {
        email.text = data?.email
        username.text = data?.userName
        website.text = data?.website
    }

    private fun setErrorDetails(message: String?) {
        email.text = message
    }

}