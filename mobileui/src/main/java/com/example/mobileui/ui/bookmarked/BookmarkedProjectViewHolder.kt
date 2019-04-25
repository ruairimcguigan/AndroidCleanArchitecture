package com.example.mobileui.ui.bookmarked

import android.content.Context
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.mobileui.R
import com.example.mobileui.model.Project
import kotlinx.android.synthetic.main.project_view.view.*

class BookmarkedProjectViewHolder(view: View): RecyclerView.ViewHolder(view) {

    private val avatarView: ImageView = view.ownerAvatarView
    private val ownerName: TextView = view.ownerNameView
    private val projectName: TextView = view.projectNameView

    fun populate(context: Context, project: Project) {
        setProjectAvatarView(context, project)
        setProjectDetails(project)
    }

    private fun setProjectDetails(project: Project) {
        ownerName.text = project.ownerName
        projectName.text = project.fullName
    }

    private fun setProjectAvatarView(context: Context, project: Project) {
        Glide.with(context)
            .load(project.ownerAvatar)
            .apply(RequestOptions.circleCropTransform())
            .into(avatarView)
    }
}