package com.example.dagger_android_practical.di

import com.example.dagger_android_practical.di.auth.AuthModule
import com.example.dagger_android_practical.di.auth.AuthScope
import com.example.dagger_android_practical.di.viewmodel.AuthViewModelModule
import com.example.dagger_android_practical.di.main.MainFragmentBuildersModule
import com.example.dagger_android_practical.di.main.MainModule
import com.example.dagger_android_practical.di.main.MainScope
import com.example.dagger_android_practical.di.viewmodel.MainViewModelsModule
import com.example.dagger_android_practical.ui.auth.AuthActivity
import com.example.dagger_android_practical.ui.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

/***
 * this will be strictly used to inject activities only
 */
@Module
abstract class ActivityBuildersModule {

    /***
     * Using this we can define that we are going to inject dependencies in these activities
     * In the background, it is creating a SubComponent for us with the given Module classes
     */
    @AuthScope
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @MainScope
    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

}