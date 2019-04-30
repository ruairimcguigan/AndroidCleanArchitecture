package com.example.mobileui.ui.bookmarked

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mobileui.R
import com.example.mobileui.model.Project
import javax.inject.Inject

class BookmarkedAdapter
@Inject constructor() : RecyclerView.Adapter<BookmarkedProjectViewHolder>() {

    private lateinit var context: Context
    var projects: List<Project> = arrayListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int):
            BookmarkedProjectViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.bookmarked_project_view, parent, false)
        context = parent.context;
        return BookmarkedProjectViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookmarkedProjectViewHolder, position: Int) {
        val project = projects[position]
        holder.populate(context, project)
    }

    override fun getItemCount(): Int {
        return projects.count()
    }
}