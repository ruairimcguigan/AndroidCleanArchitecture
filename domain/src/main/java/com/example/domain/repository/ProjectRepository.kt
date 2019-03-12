package com.example.domain.repository

import com.example.domain.model.Project
import io.reactivex.Completable
import io.reactivex.Observable

/**
 * Define rules for what needs to be implemented in order to be able to obtain
 * and manipulate the Project data model. This interface will be implemented
 * by an exterior data layer, which will implement the logic to provide the
 * use cases of the domain layer access to the data. This can be view as an
 * enforcement - a contract for access to Project data - without the need to
 * provide the wheres and hows of this data. So this will be inject for the
 * use cases, so that they will have knowledge of the methods that are available,
 * without the responsibility of how the data is accessed.
 */
interface ProjectRepository {

    fun getProjects(): Observable<List<Project>>

    fun bookmarkProject(projectId: String): Completable

    fun unbookmarkProject(projectId: String): Completable

    fun getbookmarkedProjects(): Observable<List<Project>>
}