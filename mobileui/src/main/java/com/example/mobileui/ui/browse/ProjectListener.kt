package com.example.mobileui.ui.browse

interface ProjectListener {

    fun onProjectBookmarkClicked(projectId: String)
    fun onProjectSelected(projectId: String)
}