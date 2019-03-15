package com.example.data.repository

import com.example.data.model.ProjectEntity
import io.reactivex.Observable

interface ProjectsRemote {

    fun fetchProjects(): Observable<List<ProjectEntity>>
}