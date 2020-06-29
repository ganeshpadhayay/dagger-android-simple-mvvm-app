package com.example.dagger2_practical.di

import com.example.dagger2_practical.di.auth.AuthModule
import com.example.dagger2_practical.di.auth.AuthViewModelModule
import com.example.dagger2_practical.di.main.MainFragmentBuildersModule
import com.example.dagger2_practical.di.main.MainModule
import com.example.dagger2_practical.di.main.MainViewModelsModule
import com.example.dagger2_practical.ui.auth.AuthActivity
import com.example.dagger2_practical.ui.main.MainActivity
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
    @ContributesAndroidInjector(modules = [AuthViewModelModule::class, AuthModule::class])
    abstract fun contributeAuthActivity(): AuthActivity

    @ContributesAndroidInjector(modules = [MainFragmentBuildersModule::class, MainViewModelsModule::class, MainModule::class])
    abstract fun contributeMainActivity(): MainActivity

}