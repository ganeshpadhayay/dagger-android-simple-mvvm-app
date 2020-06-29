package com.example.dagger2_practical.network.main

import com.example.dagger2_practical.models.Post
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Query

interface MainApi {

    @GET("posts/")
    fun getPostsFromUser(@Query("userId") userId: Int): Flowable<List<Post>>
}