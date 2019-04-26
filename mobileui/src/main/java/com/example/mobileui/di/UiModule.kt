package com.example.mobileui.di

import com.example.domain.executor.PostExecutionThread
import com.example.mobileui.UiThread
import com.example.mobileui.ui.ProjectsActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class UiModule {

    @Binds
    abstract fun bindPostExecutionThread(uiThread: UiThread): PostExecutionThread

    @ContributesAndroidInjector(modules = [FragmentBuilderModule::class])
    abstract fun contributesBrowseActivity(): ProjectsActivity
}