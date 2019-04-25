package com.example.mobileui.di

import android.app.Application
import com.example.cache.ProjectsCacheImpl
import com.example.cache.db.ProjectsDb
import com.example.data.repository.ProjectsCache
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class CacheModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideDB(app: Application): ProjectsDb {
            return ProjectsDb.instance(app)
        }
    }

    @Binds
    abstract fun bindProjectCache(projectsCache: ProjectsCacheImpl): ProjectsCache
}