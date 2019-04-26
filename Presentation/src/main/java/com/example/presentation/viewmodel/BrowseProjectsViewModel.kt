package com.example.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.bookmarked.BookmarkProject
import com.example.domain.interactor.bookmarked.UnbookmarkProject
import com.example.domain.interactor.fetch.FetchProjects
import com.example.domain.model.Project
import com.example.presentation.mapper.ProjectViewMapper
import com.example.presentation.model.ProjectView
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState
import io.reactivex.observers.DisposableCompletableObserver
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseProjectsViewModel
@Inject constructor(
    private val fetchProjects: FetchProjects?,
    private val bookmarkProject: BookmarkProject,
    private val unbookmarkProject: UnbookmarkProject,
    private val mapper: ProjectViewMapper) : ViewModel() {

    private val projectsLiveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    fun getProjectsLiveData(): MutableLiveData<Resource<List<ProjectView>>> {
        return projectsLiveData
    }

    fun fetchProjects() {
        projectsLiveData.postValue(
            Resource(
                ResourceState.LOADING,
                null,
                null)
        )
        fetchProjects?.execute(ProjectsSubscriber())
    }

    fun bookmarkProject(projectId: String) {
        return bookmarkProject.execute(
            BookmarkProjectsSubscriber(),
            BookmarkProject.Params.forProject(projectId)
        )
    }

    fun unbookmarkProject(projectId: String) {
        return unbookmarkProject.execute(
            BookmarkProjectsSubscriber(),
            UnbookmarkProject.Params.forProject(projectId)
        )
    }

    inner class ProjectsSubscriber : DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            projectsLiveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    t.map { mapper.mapToView(it) },
                    null)
            )
        }

        override fun onComplete() {}

        override fun onError(e: Throwable) {
            projectsLiveData.postValue(
                Resource(
                    ResourceState.ERROR,
                    null,
                    e.localizedMessage)
            )
        }
    }

    inner class BookmarkProjectsSubscriber : DisposableCompletableObserver() {
        override fun onComplete() {
            projectsLiveData.postValue(
                Resource(
                    ResourceState.SUCCESS,
                    projectsLiveData.value?.data,
                    null)
            )
        }
        override fun onError(e: Throwable) {
            projectsLiveData.postValue(
                Resource(ResourceState.ERROR, projectsLiveData.value?.data, e.localizedMessage)
            )
        }
    }

    override fun onCleared() {
        fetchProjects?.dispose()
        super.onCleared()
    }
}
