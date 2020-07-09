package com.example.dagger_android_practical

import com.example.dagger_android_practical.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    //this method returns our appComponent, previously we used to create appComponent here by not extending the DaggerApplication
    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent.builder().application(this).build()
    }

}