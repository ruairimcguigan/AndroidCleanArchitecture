package com.example.mobileui

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import com.example.mobileui.inject.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import timber.log.Timber
import javax.inject.Inject

class App: Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

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
    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}