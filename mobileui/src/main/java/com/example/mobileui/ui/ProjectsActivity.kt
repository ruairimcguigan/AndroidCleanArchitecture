package com.example.mobileui.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.mobileui.R
import com.example.mobileui.ui.browse.BrowseProjectsFragment

class ProjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.projects_activity)

        initToolbar()

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.contentFrame, BrowseProjectsFragment.newInstance())
                .commitNow()
        }
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }

}
