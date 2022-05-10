package com.example.tada_test.dependencyInjection.component

import androidx.multidex.MultiDexApplication
import com.example.tada_test.dependencyInjection.module.NetworkModule
import com.example.tada_test.dependencyInjection.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [ViewModelModule::class, NetworkModule::class])
interface AppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: BaseApp): AppComponent
    }
    fun dataListTaskComponent(): DataListTaskComponent.Factory
    fun userLoginTaskComponent(): UserLoginTaskComponent.Factory

}

class BaseApp: MultiDexApplication() {
    val appComponent = DaggerAppComponent.factory().create(this)
}