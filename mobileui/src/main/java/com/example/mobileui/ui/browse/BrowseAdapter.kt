package com.example.mobileui.ui.browse

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileui.R
import com.example.mobileui.model.Project
import javax.inject.Inject

class BrowseAdapter
@Inject constructor() : RecyclerView.Adapter<BrowseProjectViewHolder>() {

    private lateinit var context: Context

    var projectListener: ProjectListener? = null
    var projects: List<Project> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BrowseProjectViewHolder {

        val view = LayoutInflater.from(
            parent.context).inflate(
            R.layout.project_view,
            parent,
            false
        )

        context = parent.context
        return BrowseProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: BrowseProjectViewHolder, position: Int) {
        val project = projects[position]
        val id = project.id

        holder.populate(context, project)

        holder.itemView.setOnClickListener {
            if (project.isBookmarked) {
                projectListener?.onBookmarkedProjectClicked(id)
            } else {
                projectListener?.onProjectSelected(id)
            }
        }
    }

    override fun getItemCount(): Int {
        return projects.count()
    }
}