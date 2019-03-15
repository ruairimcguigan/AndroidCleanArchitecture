package com.example.data.repository

import com.example.data.model.ProjectEntity
import io.reactivex.Completable
import io.reactivex.Observable

interface ProjectsDataStore {

    fun fetchProjects(): Observable<List<ProjectEntity>>

    fun fetchBookmarkedProjects(): Observable<List<ProjectEntity>>

    fun bookmarkProject(projectId: String): Completable

    fun unBookmarkProject(projectId: String): Completable
}