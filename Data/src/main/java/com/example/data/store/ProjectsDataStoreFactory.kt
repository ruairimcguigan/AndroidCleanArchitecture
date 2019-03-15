package com.example.data.store

import com.example.data.repository.ProjectsDataStore
import javax.inject.Inject

/**
 * Class that helps decide which repo should be used
 */
open class ProjectsDataStoreFactory @Inject constructor(
    private val projectsCacheDataStoreImpl: ProjectsCacheDataStoreImpl,
    private val projectsRemoteDataStoreImpl: ProjectsRemoteDataStoreImpl
) {

    open fun getDataStore(isProjectsCached: Boolean, hasCacheExpired: Boolean): ProjectsDataStore{
        return if (isProjectsCached && !hasCacheExpired){
            projectsCacheDataStoreImpl
        } else{
            projectsRemoteDataStoreImpl
        }
    }

    open fun getCacheDataStore(): ProjectsDataStore {
        return projectsCacheDataStoreImpl
    }

    open fun getRemoteDataStore(): ProjectsDataStore {
        return projectsRemoteDataStoreImpl
    }
}