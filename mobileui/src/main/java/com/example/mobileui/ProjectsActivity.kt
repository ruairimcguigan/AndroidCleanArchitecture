package com.example.mobileui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileui.ui.browseprojects.BrowseProjectsFragment

class ProjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.projects_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, BrowseProjectsFragment.newInstance())
                .commitNow()
        }
    }

}
