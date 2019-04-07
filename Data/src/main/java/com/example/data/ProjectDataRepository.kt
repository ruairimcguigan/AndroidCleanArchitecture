package com.example.data

import com.example.data.mapper.ProjectMapper
import com.example.data.repository.ProjectsCache
import com.example.data.store.ProjectsDataStoreFactory
import com.example.domain.model.Project
import com.example.domain.repository.ProjectRepository
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import javax.inject.Inject

/**
 * Central access point for the data layer. The projects data repository
 * is used to orchestrate the flow of data between the domain module and
 * the remote / cache modules.
 */
class ProjectDataRepository @Inject constructor(
    private val mapper: ProjectMapper,
    private val cache: ProjectsCache,
    private val factory: ProjectsDataStoreFactory

): ProjectRepository {

    override fun fetchProjects(): Observable<List<Project>> {
        return Observable.zip(
            cache.areProjectsCached().toObservable(),
            cache.isProjectsCacheExpired().toObservable(),

            BiFunction<Boolean, Boolean, Pair<Boolean, Boolean>> {
                    areCached, isExpired -> Pair(areCached, isExpired)
            })
            .flatMap {
                factory.getDataStore(it.first, it.second).fetchProjects()
            }
            .flatMap { projects ->
                factory.getCacheDataStore()
                    .saveProjects(projects)
                    .andThen(Observable.just(projects))
            }
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }

    override fun bookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsBookmarked(projectId)
    }

    override fun unbookmarkProject(projectId: String): Completable {
        return factory.getCacheDataStore().setProjectAsNotBookmarked(projectId)
    }

    // map the response of fetchBookmarkedProjects() to the domain layer representation model
    override fun fetchBookmarkedProjects(): Observable<List<Project>> {
        return factory.getCacheDataStore().fetchBookmarkedProjects()
            .map {
                it.map {
                    mapper.mapFromEntity(it)
                }
            }
    }
}