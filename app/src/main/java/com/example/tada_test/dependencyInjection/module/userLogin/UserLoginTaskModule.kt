package com.example.tada_test.dependencyInjection.module.userLogin

import androidx.lifecycle.ViewModel
import com.example.tada_test.viewmodel.LoginAndRegisterViewModel
import com.example.tada_test.viewmodel.LoginAndRegisterViewModelImpl
import com.example.tada_test.dependencyInjection.module.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface UserLoginTaskModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginAndRegisterViewModelImpl::class)
    fun bindViewModelImpl(viewModel: LoginAndRegisterViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginAndRegisterViewModel::class)
    fun bindViewModel(viewModel: LoginAndRegisterViewModelImpl): ViewModel
}