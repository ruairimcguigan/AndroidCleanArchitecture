package com.example.mobileui.ui.bookmarked

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileui.R
import com.example.mobileui.mapper.ProjectViewMapper
import com.example.mobileui.model.Project
import com.example.mobileui.ui.ProjectsActivity
import com.example.mobileui.ui.browse.BrowseAdapter
import com.example.presentation.model.ProjectView
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState
import com.example.presentation.viewmodel.BookmarkedProjectsViewModel
import com.example.presentation.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.project_list.*
import timber.log.Timber
import javax.inject.Inject

class BookmarkedProjectsFragment : Fragment() {

    @Inject lateinit var adapter: BrowseAdapter
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var mapper: ProjectViewMapper

    private lateinit var viewmodel: BookmarkedProjectsViewModel

    companion object {
        fun newInstance() = BookmarkedProjectsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = ConstraintLayout(activity)
        inflater.inflate(R.layout.project_list, view, true)
        return view
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initProjectList()
        attachViewModel()
        fetchBookmarkedProjects()
    }

    override fun onResume() {
        super.onResume()
        configureBookmarkedView()
    }

    private fun initProjectList() {
        projectsList.layoutManager = LinearLayoutManager(activity)
        projectsList.adapter = adapter
    }

    private fun attachViewModel() {
        viewmodel = ViewModelProviders.of(
            this,
            viewModelFactory)
            .get(BookmarkedProjectsViewModel::class.java)
    }

    private fun fetchBookmarkedProjects() {
        viewmodel.getbrowseBookmarkedProjectsLiveData().observe(this,
            Observer<Resource<List<ProjectView>>> {
                it?.let {
                    onResponse(it)
                }
            })
        viewmodel.fetchBookmarkedProjects()
    }

    private fun onResponse(resource: Resource<List<ProjectView>>) {
        when (resource.status) {
            ResourceState.SUCCESS -> {
                onSuccess(resource.data?.map { mapper.mapToView(it) })
            }
            ResourceState.LOADING -> { onLoading() }
            ResourceState.ERROR ->{ onError(resource) }
        }
    }

    private fun onSuccess(projects: List<Project>?) {
        progress.visibility = View.GONE

        projects?.let {
            adapter.projects = it
            projectsList.visibility = View.VISIBLE
        } ?: run { }
    }

    private fun onLoading() {
        progress.visibility = View.VISIBLE
        projectsList.visibility = View.GONE
    }

    private fun onError(resource: Resource<List<ProjectView>>) {
        Timber.e(tag, resource.message)
    }

    private fun configureBookmarkedView() {
        (activity as ProjectsActivity).toggleHomeUp(true)
        (activity as ProjectsActivity).title = getString(R.string.view_bookmarked_projects_title)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initProjectList()
    }
}
