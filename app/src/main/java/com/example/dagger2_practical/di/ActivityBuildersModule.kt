package com.example.dagger2_practical.di

import com.example.dagger2_practical.di.auth.AuthViewModelModule
import com.example.dagger2_practical.ui.auth.AuthActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/***
 * this will be strictly used to inject activities only
 */
@Module
abstract class ActivityBuildersModule {
    //using this we can define that we are going to inject dependencies in these activities
    @ContributesAndroidInjector(
        modules = [AuthViewModelModule::class]
    )
    abstract fun contributeAuthActivity(): AuthActivity

}