package com.example.tada_test.dependencyInjection.module.userLogin

import com.example.tada_test.repository.DataListRepository
import com.example.tada_test.repository.DataListRepositoryImpl
import com.example.tada_test.repository.LoginAndRegisterRepository
import com.example.tada_test.repository.LoginAndRegisterRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface UserLoginRepoModule {

    @Binds
    fun bindsUserLoginRepository(repo: LoginAndRegisterRepositoryImpl): LoginAndRegisterRepository
}