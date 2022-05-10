package com.example.tada_test.dependencyInjection.module.dataList

import com.example.tada_test.repository.DataListRepository
import com.example.tada_test.repository.DataListRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataListRepoModule {

    @Binds
    fun bindsDataListRepository(repo: DataListRepositoryImpl): DataListRepository
}