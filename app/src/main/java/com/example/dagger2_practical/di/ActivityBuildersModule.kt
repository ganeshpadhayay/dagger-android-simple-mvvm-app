package com.example.dagger2_practical.di

import com.example.dagger2_practical.AuthActivity
import dagger.Module
import dagger.Provides
import dagger.android.ContributesAndroidInjector

/***
 * We keep our dependencies here and then through Components(Servers) they are served to different clients
 */
@Module
abstract class ActivityBuildersModule {

    //using this we can define that we are going to inject dependencies in these activities
    @ContributesAndroidInjector
    abstract fun contributeAuthActivity(): AuthActivity

    companion object {
        @Provides
        fun provideSomeString(): String {
            return "some string"
        }
    }

}