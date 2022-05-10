package com.example.tada_test.dependencyInjection.component

import com.example.tada_test.ui.LoginActivity
import com.example.tada_test.ui.RegisterActivity
import com.example.tada_test.dependencyInjection.module.userLogin.UserLoginDataStoreModule
import com.example.tada_test.dependencyInjection.module.userLogin.UserLoginRepoModule
import com.example.tada_test.dependencyInjection.module.userLogin.UserLoginTaskModule
import dagger.Subcomponent

@Subcomponent(
        modules = [UserLoginDataStoreModule::class,
            UserLoginRepoModule::class,
            UserLoginTaskModule::class
        ]
)
interface UserLoginTaskComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): UserLoginTaskComponent
    }

    fun inject(activity: LoginActivity)
    fun inject(activity: RegisterActivity)
}