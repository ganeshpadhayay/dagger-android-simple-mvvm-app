package com.example.dagger2_practical.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.dagger2_practical.R
import com.example.dagger2_practical.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragment : DaggerFragment() {

    companion object {
        private const val TAG = "PostsFragment"
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    private lateinit var postsViewModel: PostsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        postsViewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(PostsViewModel::class.java)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        postsViewModel.observePosts().removeObservers(viewLifecycleOwner)
        postsViewModel.observePosts().observe(viewLifecycleOwner, Observer {
            Log.d(TAG, "subscribeObservers: $it")
        })
    }


}