package com.example.dagger2_practical.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.dagger2_practical.R
import com.example.dagger2_practical.ui.main.Resource
import com.example.dagger2_practical.utils.VerticalSpacingItemDecoration
import com.example.dagger2_practical.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject


class PostsFragment : DaggerFragment() {

    companion object {
        private const val TAG = "PostsFragment"
    }

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory

    @Inject
    lateinit var postsRecyclerAdapter: PostsRecyclerAdapter

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
            if (it != null) {
                when (it.status) {
                    Resource.Status.LOADING -> {
                        Log.d(TAG, "onChanged: LOADING...")
                    }
                    Resource.Status.SUCCESS -> {
                        Log.d(TAG, "onChanged: got posts...")
                        postsRecyclerAdapter.setPosts(it.data!!)
                    }
                    Resource.Status.ERROR -> {
                        Log.e(TAG, "onChanged: ERROR..." + it.message)
                    }
                }
            }
        })
    }

    private fun iniRecyclerView() {
        recycler_view.layoutManager = LinearLayoutManager(activity)
        var itemDecoration: VerticalSpacingItemDecoration = VerticalSpacingItemDecoration(15)
        recycler_view.addItemDecoration(itemDecoration)
        recycler_view.adapter = postsRecyclerAdapter
    }


}