package com.example.data.store

import com.example.data.repository.ProjectsDataStore
import javax.inject.Inject

/**
 * Class that helps decide which repo should be used
 */
open class ProjectsDataStoreFactory @Inject constructor(
    private val projectsCacheDataStore: ProjectsCacheDataStore,
    private val projectsRemoteDataStore: ProjectsRemoteDataStore
) {

    open fun getDataStore(
        isProjectsCached: Boolean,
        hasCacheExpired: Boolean
    ): ProjectsDataStore {
        return if (isProjectsCached && !hasCacheExpired)
            projectsCacheDataStore else projectsRemoteDataStore
    }

    open fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStore
    }

    open fun getRemoteDataStore(): ProjectsDataStore {
        return projectsRemoteDataStore
    }
}