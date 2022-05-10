package com.example.tada_test.dependencyInjection.module

import com.example.tada_test.api.ApiClient
import com.example.tada_test.api.ApiInterface
import dagger.Module
import dagger.Provides

@Module
object NetworkModule {

    @Provides
    @JvmStatic
    fun provideApiInterfaceSync(): ApiInterface {
        return ApiClient.client.create(ApiInterface::class.java)
    }
}