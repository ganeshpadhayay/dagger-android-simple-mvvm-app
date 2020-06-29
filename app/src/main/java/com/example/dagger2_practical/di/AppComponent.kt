package com.example.dagger2_practical.di

import android.app.Application
import com.example.dagger2_practical.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule

/***
 * AndroidInjector is basically taking the DI to next level and setting up the client(BaseApplication) and Server(AppComponent) hierarchy
 * In general, the components are servers and serve dependencies and activity/fragments are clients who consume these dependencies
 * If we have SubComponents in the system then SubComponents can also act as clients
 */
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuildersModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    //we are overriding the Builder so that we can customize it
    @Component.Builder
    interface Builder {

        //this is used to bind a particular instance of an object to a component at the time of component's creation
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

}