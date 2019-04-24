package com.example.mobileui.di

import android.app.Application
import com.example.mobileui.App
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidInjectionModule::class])
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun app(application: Application): Builder
        fun build(): AppComponent
    }

    fun inject(app: App)
}