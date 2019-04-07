package com.example.cache

import com.example.cache.db.ProjectsDb
import com.example.cache.mapper.CachedProjectMapper
import com.example.cache.model.Config
import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsCache
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
import javax.inject.Inject

class ProjectsCacheImpl @Inject constructor(
    private val projectsDB: ProjectsDb,
    private val mapper: CachedProjectMapper): ProjectsCache {

    override fun clearProjects(): Completable {
        return Completable.defer {
            projectsDB.cachedProjectsDao().deleteProjects()
                Completable.complete()
        }
    }

    override fun saveProjects(projects: List<ProjectEntity>): Completable {
        return Completable.defer {
            projectsDB.cachedProjectsDao().insertProjects(
                projects.map { mapper.mapToCache(it) })
            Completable.complete()
        }
    }

    override fun fetchProjects(): Observable<List<ProjectEntity>> {
        return projectsDB.cachedProjectsDao().getBookmarkedProjects()
            .map {
                it.map { mapper.mapFromCached(it) }
            }
    }

    override fun getBookmarkedProjects(): Observable<List<ProjectEntity>> {
        return projectsDB.cachedProjectsDao().getBookmarkedProjects()
            .map {
                it.map {
                    mapper.mapFromCached(it)
                }
            }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return Completable.defer {
            projectsDB.cachedProjectsDao().setBookmarkStatus(true, projectId)
            Completable.complete()
        }
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return Completable.defer {
            projectsDB.cachedProjectsDao().setBookmarkStatus(false, projectId)
            Completable.complete()
        }
    }

    override fun areProjectsCached(): Single<Boolean> {
        return projectsDB.cachedProjectsDao()
            .fetchProjects().isEmpty
            .map { !it }
    }

    override fun setLastCacheTime(lastCached: Long): Completable {
        return Completable.defer {
            projectsDB.configDao().insertConfig(Config(lastCacheTime = lastCached))
            Completable.complete()
        }
    }

    override fun isProjectsCacheExpired(): Single<Boolean> {
        val currentTime = System.currentTimeMillis()
        val expirationTime = (60 * 10 * 1000).toLong()
        return projectsDB.configDao().getConfig()
            .single(Config(lastCacheTime = 0))
            .map {
                currentTime - it.lastCacheTime > expirationTime
            }
    }
}