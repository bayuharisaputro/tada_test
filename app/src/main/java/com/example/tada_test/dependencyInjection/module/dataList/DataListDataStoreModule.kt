package com.example.tada_test.dependencyInjection.module.dataList

import com.example.tada_test.datastore.DataListRemoteDataStore
import com.example.tada_test.datastore.DataListRemoteDataStoreImpl
import dagger.Binds
import dagger.Module

@Module
interface DataListDataStoreModule {

    @Binds
    fun bindsDataListRemoteDataStore(remoteDataStore: DataListRemoteDataStoreImpl): DataListRemoteDataStore
}