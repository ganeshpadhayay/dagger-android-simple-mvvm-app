package com.example.dagger2_practical.di.auth

import androidx.lifecycle.ViewModel
import com.example.dagger2_practical.di.ViewModelKey
import com.example.dagger2_practical.ui.auth.AuthViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class AuthViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(AuthViewModel::class)
    abstract fun bindAuthViewModel(authViewModel: AuthViewModel): ViewModel
}