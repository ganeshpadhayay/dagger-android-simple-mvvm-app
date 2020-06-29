package com.example.dagger2_practical.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.dagger2_practical.R
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

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
        @Singleton
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background)
        }

        @Singleton
        @Provides
        fun provideGlideInstance(
            application: Application,
            requestOptions: RequestOptions
        ): RequestManager {
            return Glide.with(application).setDefaultRequestOptions(requestOptions)
        }

        @Singleton
        @Provides
        fun provideAppDrawable(application: Application): Drawable {
            return ContextCompat.getDrawable(application, R.drawable.logo)!!
        }
    }

}