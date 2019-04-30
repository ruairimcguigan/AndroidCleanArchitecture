package com.example.mobileui.ui.browse

interface ProjectListener {

    fun onBookmarkedProjectClicked(projectId: String)
    fun onProjectSelected(projectId: String)
}