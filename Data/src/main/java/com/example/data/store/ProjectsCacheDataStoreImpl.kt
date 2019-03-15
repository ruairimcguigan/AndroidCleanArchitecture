package com.example.data.store

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsCache
import com.example.data.repository.ProjectsDataStore
import io.reactivex.Completable
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsCacheDataStoreImpl @Inject constructor(private val projectsCache: ProjectsCache): ProjectsDataStore {

    override fun fetchProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.fetchProjects()
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return projectsCache.saveProjects(projects)
            .andThen(projectsCache.setLastCacheTime(System.currentTimeMillis()))
    }

    override fun clearProjects(): Completable {
        return projectsCache.clearProjects()
    }

    override fun fetchBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsCache.getBookmarkedProjects()
    }

    override fun setProjectAsBookmarked(projectId: String): Completable {
        return projectsCache.bookmarkProject(projectId)
    }

    override fun setProjectAsNotBookmarked(projectId: String): Completable {
        return projectsCache.unbookmarkProject(projectId)
    }


}