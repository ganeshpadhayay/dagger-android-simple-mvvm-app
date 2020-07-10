package com.example.dagger_android_practical.di

import android.app.Application
import com.example.dagger_android_practical.BaseApplication
import com.example.dagger_android_practical.SessionManager
import com.example.dagger_android_practical.di.viewmodel.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/***
 * AndroidInjector is basically taking the DI to next level and setting up the client(BaseApplication) and Server(AppComponent) hierarchy
 * In general, the components are servers and serve dependencies and activity/fragments are clients who consume these dependencies
 * If we have SubComponents in the system then SubComponents can also act as clients
 */
@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, ActivityBuildersModule::class, AppModule::class, ViewModelFactoryModule::class])
interface AppComponent : AndroidInjector<BaseApplication> {

    fun sessionManager(): SessionManager

    //we are overriding the Builder so that we can customize it
    @Component.Builder
    interface Builder {

        //this is used to bind a particular instance of an object to a component at the time of component's creation
        @BindsInstance
        fun application(app: Application): Builder

        fun build(): AppComponent
    }

}


//NOTE: Singleton is just a regular scope then how is ensures that objects are singleton?
//It is because of the fact that appComponent lives through out the app lifecycle and since we have attached Singleton with
//appComponent which basically means that appComponent owns this scope and hence all the dependencies with Singleton scope would
//become singleton like appComponent, for Dagger it is just a label like any other random scope