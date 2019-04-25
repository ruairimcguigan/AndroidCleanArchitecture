package com.example.mobileui.ui.browse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.mobileui.R
import com.example.mobileui.model.Project
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.project_list.*

class BrowseProjectsFragment : Fragment() {

//    @Inject private lateinit var viewModelFactory: ViewModelFactory
//    @Inject private lateinit var viewmodel: BrowseProjectsViewModel
//    @Inject lateinit var mapper: ProjectViewMapper

    private lateinit var adapter: BrowseAdapter
    private var listener: BrowseListener? = null

    companion object {
        fun newInstance() = BrowseProjectsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = ConstraintLayout(activity)
        return inflater.inflate(R.layout.project_list, view, true)
    }

//    override fun onActivityCreated(savedInstanceState: Bundle?) {
//        super.onActivityCreated(savedInstanceState)
//
//        attachViewModel()
//        initProjectList()
//    }

//    override fun onStart() {
//        super.onStart()
//        fetchProjects()
//    }

//    private fun attachViewModel() {
//        viewmodel = ViewModelProviders.of(
//            this,
//            viewModelFactory)
//            .get(BrowseProjectsViewModel::class.java)
//    }

//    private fun initProjectList() {
//        adapter = BrowseAdapter()
//        adapter.projectListener = projectListener
//        projectsList.layoutManager = LinearLayoutManager(activity)
//    }

//    private fun fetchProjects() {
//        viewmodel.getProjectsLiveData().observe(this,
//            Observer<Resource<List<ProjectView>>> {
//                it?.let {
//                    handleDataState(it)
//                }
//            })
//        viewmodel.fetchProjects()
//    }

//    private fun handleDataState(resource: Resource<List<ProjectView>>) {
//        when (resource.status) {
//            ResourceState.SUCCESS -> {
//                onSuccess(resource.data?.map {
//                    mapper.mapToView(it)
//                })
//            }
//            ResourceState.LOADING -> {
//                progress.visibility = View.VISIBLE
//                projectsList.visibility = View.GONE
//            }
//            else -> Timber.d("No projects received")
//        }
//    }

    private fun onSuccess(projects: List<Project>?) {
        progress.visibility = View.GONE
        projects?.let {
            adapter.data = it as MutableList<Project>
            adapter.notifyDataSetChanged()
            projectsList.visibility = View.VISIBLE
        } ?: run {

        }
    }

    fun bookmarkProject(projectId: String) {
        listener?.onBrowseInteraction(projectId)
    }

//    private val projectListener = object : ProjectListener {
//        override fun onProjectBookmarkClicked(projectId: String) {
//            viewmodel.unbookmarkProject(projectId)
//        }
//
//        override fun onProjectSelected(projectId: String) {
//            viewmodel.bookmarkProject(projectId)
//        }
//    }

    override fun onAttach(context: Context) {
//        AndroidSupportInjection.inject(this)
        super.onAttach(context)
        if (context is BrowseListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement BookmarkListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface BrowseListener {
        fun onBrowseInteraction(projectId: String)
    }

}
