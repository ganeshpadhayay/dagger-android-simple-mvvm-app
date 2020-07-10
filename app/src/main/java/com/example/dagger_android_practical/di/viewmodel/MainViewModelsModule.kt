package com.example.dagger_android_practical.di.viewmodel

import androidx.lifecycle.ViewModel
import com.example.dagger_android_practical.di.viewmodel.ViewModelKey
import com.example.dagger_android_practical.ui.main.posts.PostsViewModel
import com.example.dagger_android_practical.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel::class)
    abstract fun bindPostsViewModel(postsViewModel: PostsViewModel): ViewModel
}