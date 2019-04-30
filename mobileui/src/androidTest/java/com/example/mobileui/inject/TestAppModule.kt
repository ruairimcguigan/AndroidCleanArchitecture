package com.example.mobileui.inject

import android.app.Application
import android.content.Context
import dagger.Binds
import dagger.Module

@Module
abstract class TestAppModule {

    @Binds
    abstract fun bindContext(application: Application): Context

}