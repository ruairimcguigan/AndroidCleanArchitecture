package com.example.mobileui

import android.app.Activity
import android.app.Application
import androidx.fragment.app.Fragment
import androidx.test.InstrumentationRegistry
import com.example.mobileui.inject.DaggerTestAppComponent
import com.example.mobileui.inject.TestAppComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class TestApp : Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>
    @Inject lateinit var fragmentInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var appComponent: TestAppComponent

    companion object {
        fun appComponent(): TestAppComponent {
            return (InstrumentationRegistry.getTargetContext().applicationContext
                    as TestApp).appComponent
        }
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerTestAppComponent
            .builder()
            .application(this)
            .build()

        appComponent.inject(this)
    }


    override fun activityInjector(): AndroidInjector<Activity> {
        return activityInjector
    }

    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return fragmentInjector
    }
}