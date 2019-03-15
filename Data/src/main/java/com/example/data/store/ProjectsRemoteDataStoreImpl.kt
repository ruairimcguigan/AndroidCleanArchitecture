package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsDataStore
import com.example.data.repository.ProjectsRemote
import io.reactivex.Completable
import io.reactivex.Observable
import java.lang.UnsupportedOperationException
import javax.inject.Inject

class ProjectsRemoteDataStoreImpl @Inject constructor(private val projectsRemote: ProjectsRemote) : ProjectsDataStore {
    override fun fetchProjects(): Observable<List<ProjectEntity>> {
        return projectsRemote.fetchProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        throw UnsupportedOperationException("Saving projects isn't supported for remote call")
    }

    override fun clearProjects(): Completable {
        throw UnsupportedOperationException("Clearing projects isn't supported for remote call")
    }

    override fun fetchBookmarkedProjects(): Observable<List<ProjectEntity>> {
        throw UnsupportedOperationException("Fetching bookmarked projects isn't supported for remote call")
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Bookmarking projects isn't supported for remote call")
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        throw UnsupportedOperationException("Unbookmarking projects isn't supported for remote call")
    }
}