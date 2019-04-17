package com.example.presentation.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.domain.interactor.bookmarked.FetchBookmarkedProjects
import com.example.domain.model.Project
import com.example.presentation.mapper.ProjectViewMapper
import com.example.presentation.model.ProjectView
import com.example.presentation.state.ResourceState
import com.example.presentation.testdata.TestDataFactory.randomString
import com.example.presentation.testdata.TestProjectFactory.makeProjectList
import com.example.presentation.testdata.TestProjectFactory.makeProjectViewList
import com.nhaarman.mockitokotlin2.*
import io.reactivex.observers.DisposableObserver
import junit.framework.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.mockito4kotlin.annotation.Captor

class BrowseBookmarkedProjectsViewmodelTest {

    @get:Rule var instantTaskExecutorRule = InstantTaskExecutorRule()

    var bookmarkedProjects = mock<FetchBookmarkedProjects>()
    var mapper = mock<ProjectViewMapper>()

    private var viewModel = BrowseBookmarkedProjectsViewModel(bookmarkedProjects, mapper)

    @Captor val captor = argumentCaptor<DisposableObserver<List<Project>>>()

    @Test
    fun `test fetch bookmarked projects executes use case`() {
        // when
        viewModel.fetchBookmarkedProjects()

        // then
        verify(bookmarkedProjects, times(1)).execute(any(), eq(null))
    }

    @Test
    fun `test fetch bookmarked projects returns success`() {
        // given
        val projects = makeProjectList(2)
        val projectViews = makeProjectViewList(2)
        stubProjectMapperMapToView(projectViews[0], projects[0])
        stubProjectMapperMapToView(projectViews[1], projects[1])

        // when
        viewModel.fetchBookmarkedProjects()

        // then
        verify(bookmarkedProjects).execute(captor.capture(), eq(null))

        // and when
        captor.firstValue.onNext(projects)

        // then
        assertEquals(
            ResourceState.SUCCESS,
            viewModel.getbrowseBookmarkedProjectsLiveData().value?.status)
    }

    @Test
    fun `test fetch bookamrked projects successfully returns project data`() {
        // given
        val projects = makeProjectList(2)
        val projectViews = makeProjectViewList(2)
        stubProjectMapperMapToView(projectViews[0], projects[0])
        stubProjectMapperMapToView(projectViews[1], projects[1])

        // when
        viewModel.fetchBookmarkedProjects()

        // then
        verify(bookmarkedProjects).execute(captor.capture(), eq(null))

        // and when
        captor.firstValue.onNext(projects)

        // then
        assertEquals(projectViews,
            viewModel.getbrowseBookmarkedProjectsLiveData().value?.data)
    }

    @Test
    fun `test fetch bookmarked projects returns error`() {
        // when
        viewModel.fetchBookmarkedProjects()

        // then
        verify(bookmarkedProjects).execute(captor.capture(), eq(null))

        // and when
        captor.firstValue.onError(RuntimeException())

        // then
        assertEquals(ResourceState.ERROR,
            viewModel.getbrowseBookmarkedProjectsLiveData().value?.status)
    }

    @Test
    fun `test fetch bookmarked projects returns message for error`() {
        // given
        val errorMessage = randomString()

        // when
        viewModel.fetchBookmarkedProjects()

        // then
        verify(bookmarkedProjects).execute(captor.capture(), eq(null))

        // and when
        captor.firstValue.onError(RuntimeException(errorMessage))

        // then
        assertEquals(errorMessage,
            viewModel.getbrowseBookmarkedProjectsLiveData().value?.message)
    }

    private fun stubProjectMapperMapToView(projectView: ProjectView,
                                           project: Project) {
        whenever(mapper.mapToView(project))
            .thenReturn(projectView)
    }

}