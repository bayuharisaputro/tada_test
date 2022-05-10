package com.example.tada_test.dependencyInjection.component

import com.example.tada_test.dependencyInjection.module.dataList.DataListDataStoreModule
import com.example.tada_test.dependencyInjection.module.dataList.DataListRepoModule
import com.example.tada_test.dependencyInjection.module.dataList.DataListTaskModule
import com.example.tada_test.ui.home.HomeFragment
import dagger.Subcomponent

@Subcomponent(
        modules = [DataListDataStoreModule::class,
            DataListRepoModule::class,
            DataListTaskModule::class
        ]
)
interface DataListTaskComponent {
    @Subcomponent.Factory
    interface Factory {
        fun create(): DataListTaskComponent
    }

    fun inject(fragment: HomeFragment)
}