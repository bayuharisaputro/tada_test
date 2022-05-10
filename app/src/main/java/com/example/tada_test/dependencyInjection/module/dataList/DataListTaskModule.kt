package com.example.tada_test.dependencyInjection.module.dataList

import androidx.lifecycle.ViewModel
import com.example.tada_test.viewmodel.DataListViewModel
import com.example.tada_test.viewmodel.DataListViewModelImpl
import com.example.tada_test.dependencyInjection.module.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface DataListTaskModule {
    @Binds
    @IntoMap
    @ViewModelKey(DataListViewModelImpl::class)
    fun bindViewModelImpl(viewModel: DataListViewModelImpl): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DataListViewModel::class)
    fun bindViewModel(viewModel: DataListViewModelImpl): ViewModel
}