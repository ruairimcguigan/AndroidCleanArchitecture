package com.example.mobileui.inject

import com.example.data.repository.ProjectsRemote
import com.example.mobileui.BuildConfig
import com.example.remote.api.GithubTrendingApi
import com.example.remote.api.GithubTrendingApiFactory
import com.example.remote.mapper.ProjectsRemoteImpl
import dagger.Binds
import dagger.Module
import dagger.Provides

@Module
abstract class RemoteModule {

    @Module
    companion object {
        @Provides
        @JvmStatic
        fun provideApiService(): GithubTrendingApi {
            return GithubTrendingApiFactory.makeGithubTrendingService(BuildConfig.DEBUG)
        }
    }

    @Binds
    abstract fun bindProjectsRemote(projectsRemote: ProjectsRemoteImpl): ProjectsRemote
}