package com.example.dagger2_practical.di.auth

import com.example.dagger2_practical.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module
class AuthModule {

    /***
     * this is the main usage of access of parent component's dependencies as here we are using Retrofit from appComponent
     * and using that to create our local dependency for local AuthSubComponent
     */
    companion object {
        @Provides
        fun provideAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }
    }

}