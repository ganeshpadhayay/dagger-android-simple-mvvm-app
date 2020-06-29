package com.example.dagger2_practical.di

import android.app.Application
import dagger.Module
import dagger.Provides

/***
 * We keep our dependencies here and then through Components(Servers) they are served to different clients
 *
 * Put all your app level dependencies here like Retrofit, DB, SharedPrefs, ImageLoader(Glide)
 *
 * Using static(Companions) is preferred as Dagger can internally call these without creating an object of this class
 */
@Module
class AppModule {

    companion object {
        @Provides
        fun provideSomeString(): String {
            return "some string"
        }

        //we will have this application instance with us as we are binding it with our component at the time of its creation
        @Provides
        fun getApp(application: Application): Boolean {
            return application == null
        }

        @Provides
        fun someInt(string: String): Int {
            return if (string.equals("some string", true))
                1
            else 0
        }
    }

}