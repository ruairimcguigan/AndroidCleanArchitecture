package com.example.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.interactor.bookmarked.BookmarkProject
import com.example.domain.interactor.bookmarked.UnbookmarkProject
import com.example.domain.interactor.fetch.FetchProjects
import com.example.domain.model.Project
import com.example.presentation.mapper.ProjectViewMapper
import com.example.presentation.model.ProjectView
import com.example.presentation.state.ResourceState
import com.example.presentation.testdata.TestDataFactory.randomString
import com.example.presentation.testdata.TestProjectFactory.makeProjectList
import com.example.presentation.testdata.TestProjectFactory.makeProjectViewList
import com.nhaarman.mockito_kotlin.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito4kotlin.annotation.Captor

class BrowseProjectsViewmodelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    private var fetchProjects = mock<FetchProjects>()
    private var bookmarkProject = mock<BookmarkProject>()
    private var unbookmarkProject = mock<UnbookmarkProject>()
    private var projectMapper = mock<ProjectViewMapper>()

    private var projectViewModel = BrowseProjectsViewModel(fetchProjects,
        bookmarkProject, unbookmarkProject, projectMapper)

    @Captor
    private val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Test
    fun `test fetch projects executes use case`() {
        // when
        projectViewModel.fetchProjects()

        // then
        verify(fetchProjects, times(1)).execute(any(), eq(null))
    }

    @Test
    fun `test fetch projects returns success`() {
        // given
        val projects = makeProjectList(2)
        val projectViews = makeProjectViewList(2)
        stubProjectMapperMapToView(projectViews[0], projects[0])
        stubProjectMapperMapToView(projectViews[1], projects[1])

        // when
        projectViewModel.fetchProjects()

        // then
        verify(fetchProjects).execute(captor.capture(), eq(null))

        // an when
        captor.firstValue.onNext(projects)

        // then
        assertEquals(ResourceState.SUCCESS,
            projectViewModel.getProjectsLiveData().value?.status)
    }

    @Test
    fun `test fetch projects successfully returns project data`() {
        // given
        val projects = makeProjectList(2)
        val projectViews = makeProjectViewList(2)
        stubProjectMapperMapToView(projectViews[0], projects[0])
        stubProjectMapperMapToView(projectViews[1], projects[1])

        // when
        projectViewModel.fetchProjects()

        // then
        verify(fetchProjects).execute(captor.capture(), eq(null))

        // and when
        captor.firstValue.onNext(projects)

        // then
        assertEquals(projectViews,
            projectViewModel.getProjectsLiveData().value?.data)
    }

    @Test
    fun `test fetch projects returns error`() {
        // when
        projectViewModel.fetchProjects()

        // then
        verify(fetchProjects).execute(captor.capture(), eq(null))

        // and when
        captor.firstValue.onError(RuntimeException())

        // then
        assertEquals(ResourceState.ERROR,
            projectViewModel.getProjectsLiveData().value?.status)
    }

    @Test
    fun fetchProjectsReturnsMessageForError() {
        // given
        val errorMessage = randomString()

        // when
        projectViewModel.fetchProjects()

        // then
        verify(fetchProjects).execute(captor.capture(), eq(null))

        // and when
        captor.firstValue.onError(RuntimeException(errorMessage))

        // then
        assertEquals(errorMessage, projectViewModel.getProjectsLiveData().value?.message)
    }

    private fun stubProjectMapperMapToView(projectView: ProjectView, project: Project ) {
        whenever(projectMapper.mapToView(project))
            .thenReturn(projectView)
    }
}