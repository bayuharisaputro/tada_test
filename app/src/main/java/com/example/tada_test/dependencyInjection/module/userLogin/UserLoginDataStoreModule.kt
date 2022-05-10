package com.example.tada_test.dependencyInjection.module.userLogin

import com.example.tada_test.datastore.LoginAndRegisterLocalDataStore
import com.example.tada_test.datastore.LoginAndRegisterLocalDataStoreImpl
import dagger.Binds
import dagger.Module

@Module
interface UserLoginDataStoreModule {

    @Binds
    fun bindsUserLoginLocalDataStore(localDataStore: LoginAndRegisterLocalDataStoreImpl): LoginAndRegisterLocalDataStore
}