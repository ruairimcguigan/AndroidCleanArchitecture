package com.example.remote.mapper

import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsRemote
import com.example.remote.api.GithubTrendingApi
import io.reactivex.Observable
import javax.inject.Inject

class ProjectsRemoteImpl @Inject constructor(
    private val service: GithubTrendingApi,
    private val mapper: ProjectsResponseModelMapper
): ProjectsRemote {

    override fun fetchProjects(): Observable<List<ProjectEntity>> {
        return service.searchRepositories("language:kotlin", "stars", "desc")
            .map { projectModel ->
                projectModel.items.map {
                    mapper.mapFromModel(it)
                }
            }

    }
}