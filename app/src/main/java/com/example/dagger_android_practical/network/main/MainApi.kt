package com.example.dagger_android_practical.network.main

import com.example.dagger_android_practical.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {
    @GET("posts/")
    fun getPostsFromUser(@Query("userId") userId: Int): Flowable<List<Post>>
}