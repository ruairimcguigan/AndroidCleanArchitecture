package com.example.mobileui.ui.bookmarked

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.mobileui.R
import com.example.mobileui.model.Project
import com.example.mobileui.ui.BaseAdapter
import com.example.mobileui.ui.browse.ProjectListener
import javax.inject.Inject

class BookmarkedAdapter @Inject constructor() : BaseAdapter<Project, BookmarkedProjectViewHolder>() {

    private lateinit var context: Context
    private lateinit var projectListener: ProjectListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookmarkedProjectViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.project_view, parent, false)
        context = parent.context;
        return BookmarkedProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkedProjectViewHolder, position: Int) {
        val project = data.get(position)
        holder.populate(context, project)
        holder.itemView.setOnClickListener {
            if (project.isBookmarked) {
                projectListener.onProjectBookmarkClicked(project.id)
            } else {
                projectListener.onProjectSelected(project.id)
            }
        }
    }
}