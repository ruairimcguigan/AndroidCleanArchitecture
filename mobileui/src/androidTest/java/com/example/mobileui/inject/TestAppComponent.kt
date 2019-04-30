package com.example.mobileui.inject

import android.app.Application
import com.example.domain.repository.ProjectRepository
import com.example.mobileui.TestApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidSupportInjectionModule::class,
    TestAppModule::class,
    TestCacheModule::class,
    TestDataModule::class,
    TestRemoteModule::class,
    PresentationModule::class,
    UiModule::class,
    FragmentBuilderModule::class
])
interface TestAppComponent {

    fun projectsRepository(): ProjectRepository

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): TestAppComponent
    }

    fun inject(application: TestApp)

}