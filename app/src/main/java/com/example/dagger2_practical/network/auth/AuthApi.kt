package com.example.dagger2_practical.network.auth

import com.example.dagger2_practical.models.User
import io.reactivex.Flowable
import retrofit2.http.GET
import retrofit2.http.Path

interface AuthApi {

    @GET("users/{id}")
    fun getUser(@Path("id") id: Int): Flowable<User>

}