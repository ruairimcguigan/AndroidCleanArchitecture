package com.example.mobileui.ui.browse

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mobileui.R
import com.example.mobileui.model.Project
import com.example.mobileui.ui.BaseAdapter
import javax.inject.Inject

class BrowseAdapter
@Inject constructor() : BaseAdapter<Project, BrowseProjectViewHolder>() {

    lateinit var context: Context
    var projectListener: ProjectListener? = null

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
        val project = data[position]
        val id = project.id

        holder.populate(context, project)
        holder.itemView.setOnClickListener {
            if (project.isBookmarked) {
                projectListener?.onProjectBookmarkClicked(id)
            } else {
                projectListener?.onProjectSelected(id)
            }
        }
    }
}