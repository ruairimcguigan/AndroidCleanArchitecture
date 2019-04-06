package com.example.remote

import com.example.data.model.ProjectEntity
import com.example.remote.api.GithubTrendingApi
import com.example.remote.factory.TestProjectDataFactory.makeProjectEntity
import com.example.remote.factory.TestProjectDataFactory.makeProjectModel
import com.example.remote.factory.TestProjectDataFactory.makeProjectsResponse
import com.example.remote.mapper.ProjectsRemoteImpl
import com.example.remote.mapper.ProjectsResponseModelMapper
import com.example.remote.model.ProjectModel
import com.example.remote.model.ProjectsResponseModel
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test

class ProjectRemoteImplTest {

    private val mapper = mock<ProjectsResponseModelMapper>()
    private val service = mock<GithubTrendingApi>()

    private lateinit var remote: ProjectsRemoteImpl

    @Before
    fun setUp() {
        remote = ProjectsRemoteImpl(service, mapper)
    }

    @Test
    fun fetchProjectsCallsServer() {
        // given
        stubGithubTrendingApiSearchRepos(Observable.just(makeProjectsResponse()))
        stubProjectsResponseMapperModelFromModel(makeProjectModel(), makeProjectEntity())

        // when
        remote.fetchProjects().test()

        // then
        verify(service).searchRepositories(any(),any(),any())
    }

    @Test
    fun `test fetch projects completes successfully`() {
        // given
        stubGithubTrendingApiSearchRepos(Observable.just(makeProjectsResponse()))
        stubProjectsResponseMapperModelFromModel(makeProjectModel(), makeProjectEntity())

        // when
        val testObserver = remote.fetchProjects().test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun `test fetchProjects returns projects successfully`() {
        // given
        val response = makeProjectsResponse()
        val entities = mutableListOf<ProjectEntity>()
        stubGithubTrendingApiSearchRepos(Observable.just(response))

        response.items.forEach {
            val entity = makeProjectEntity()
            entities.add(entity)
            stubProjectsResponseMapperModelFromModel(it, entity)
        }

        // when
        val testObserver = remote.fetchProjects().test()

        // then
        testObserver.assertValue(entities)
    }

    @Test
    fun fetchProjectsCallsServerWithCorrectParameters() {
        // given
        stubGithubTrendingApiSearchRepos(Observable.just(makeProjectsResponse()))
        stubProjectsResponseMapperModelFromModel(makeProjectModel(), makeProjectEntity())

        // when
        remote.fetchProjects().test()

        // then
        verify(service).searchRepositories("language:kotlin", "stars", "desc")
    }

    private fun stubGithubTrendingApiSearchRepos(observable: Observable<ProjectsResponseModel>) {
        whenever(service.searchRepositories(any(), any(), any()))
            .thenReturn(observable)
    }

    private fun stubProjectsResponseMapperModelFromModel(
        model: ProjectModel,
        entity: ProjectEntity) {
        whenever(mapper.mapFromModel(model))
            .thenReturn(entity)
    }
}