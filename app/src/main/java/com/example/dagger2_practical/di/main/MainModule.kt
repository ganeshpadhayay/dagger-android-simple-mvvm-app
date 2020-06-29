package com.example.dagger2_practical.di.main

import com.example.dagger2_practical.network.main.MainApi
import com.example.dagger2_practical.ui.main.posts.PostsRecyclerAdapter
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class MainModule {
    companion object {
        @MainScope
        @Provides
        fun providePostsRecyclerAdapter(): PostsRecyclerAdapter {
            return PostsRecyclerAdapter()
        }

        @MainScope
        @Provides
        fun provideMainApi(retrofit: Retrofit): MainApi {
            return retrofit.create(MainApi::class.java)
        }
    }
}