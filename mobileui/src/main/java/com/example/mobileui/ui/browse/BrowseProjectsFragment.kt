package com.example.mobileui.ui.browse

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
import com.example.mobileui.R
import com.example.mobileui.mapper.ProjectViewMapper
import com.example.mobileui.model.Project
import com.example.mobileui.ui.ProjectsActivity
import com.example.presentation.model.ProjectView
import com.example.presentation.state.Resource
import com.example.presentation.state.ResourceState
import com.example.presentation.viewmodel.BrowseProjectsViewModel
import com.example.presentation.viewmodel.ViewModelFactory
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.project_list.*
import timber.log.Timber
import javax.inject.Inject

class BrowseProjectsFragment : Fragment() {

    @Inject lateinit var adapter: BrowseAdapter
    @Inject lateinit var viewModelFactory: ViewModelFactory
    @Inject lateinit var mapper: ProjectViewMapper

    private lateinit var viewmodel: BrowseProjectsViewModel

    companion object {
        fun newInstance() = BrowseProjectsFragment()
    }

    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        return inflater.inflate(
            R.layout.project_list,
            ConstraintLayout(activity),
            true)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initProjectList()
        attachViewModel()
        fetchProjects()
    }

    override fun onResume() {
        super.onResume()
        configureBrowseView()
    }

    private fun initProjectList() {
        adapter.projectListener = projectListener
        projectsList.layoutManager = LinearLayoutManager(activity)
        projectsList.adapter = adapter
    }

    private fun attachViewModel() {
        viewmodel = ViewModelProviders.of(
            this,
            viewModelFactory)
            .get(BrowseProjectsViewModel::class.java)
    }

    private fun fetchProjects() {
        viewmodel.getProjectsLiveData().observe(this,
            Observer<Resource<List<ProjectView>>> {
                it?.let {
                    onResponse(it)
                }
            })
        viewmodel.fetchProjects()
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

    private fun configureBrowseView() {
        (activity as ProjectsActivity).toggleHomeUp(false)
        (activity as ProjectsActivity).title = getString(R.string.view_projects_title)
    }

    private val projectListener = object : ProjectListener {
        override fun onBookmarkedProjectClicked(projectId: String) {
            adapter.projectListener
            viewmodel.unbookmarkProject(projectId)
        }

        override fun onProjectSelected(projectId: String) {
            viewmodel.bookmarkProject(projectId)
            adapter.projectListener
        }
    }
}
