package com.example.mobileui

import android.app.Activity
import android.app.Application
import com.example.mobileui.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class App: Application(), HasActivityInjector {

    @Inject lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }

    override fun onCreate() {
        super.onCreate()

        initInjection()
        initLogging()
    }

    private fun initInjection() {
        DaggerAppComponent
            .builder()
            .app(this)
            .build()
            .inject(this)
    }

    private fun initLogging() {
        Timber.plant(Timber.DebugTree())

    }
}