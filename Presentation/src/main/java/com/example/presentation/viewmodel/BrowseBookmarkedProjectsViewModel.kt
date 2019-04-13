package com.example.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.domain.interactor.bookmarked.FetchBookmarkedProjects
import com.example.domain.model.Project
import com.example.presentation.mapper.ProjectViewMapper
import com.example.presentation.model.ProjectView
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState.*
import io.reactivex.observers.DisposableObserver
import javax.inject.Inject

class BrowseBookmarkedProjectsViewModel @Inject constructor(
    private val fetchBookmarkedProjects: FetchBookmarkedProjects,
    private val mapper: ProjectViewMapper) : ViewModel() {

    private val browseBookmarkedProjectsLiveData: MutableLiveData<Resource<List<ProjectView>>> = MutableLiveData()

    fun getbrowseBookmarkedProjectsLiveData(): LiveData<Resource<List<ProjectView>>> {
        return browseBookmarkedProjectsLiveData
    }

    fun fetchBookmarkedProjects() {
        browseBookmarkedProjectsLiveData.postValue(Resource(LOADING, null, null))
        return fetchBookmarkedProjects.execute(BookmarkedProjectsSubscriber())
    }

    inner class BookmarkedProjectsSubscriber: DisposableObserver<List<Project>>() {
        override fun onNext(t: List<Project>) {
            browseBookmarkedProjectsLiveData.postValue(
                Resource(SUCCESS,
                    t.map { mapper.mapToView(it) }, null))
        }

        override fun onError(e: Throwable) {
            browseBookmarkedProjectsLiveData.postValue(Resource(ERROR, null,
                e.localizedMessage))
        }

        override fun onComplete() { }

    }

    override fun onCleared() {
        fetchBookmarkedProjects.dispose()
        super.onCleared()
    }
}