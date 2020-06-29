package com.example.dagger2_practical.di.main

import androidx.lifecycle.ViewModel
import com.example.dagger2_practical.di.ViewModelKey
import com.example.dagger2_practical.ui.main.profile.ProfileViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class MainViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel
}