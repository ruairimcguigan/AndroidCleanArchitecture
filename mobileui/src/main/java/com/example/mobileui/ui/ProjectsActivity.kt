package com.example.mobileui.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobileui.R
import com.example.mobileui.ui.bookmarked.BookmarkedProjectsFragment.BookmarkListener
import com.example.mobileui.ui.browse.BrowseProjectsFragment

class ProjectsActivity : AppCompatActivity(), BookmarkListener, BrowseProjectsFragment.BrowseListener {

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

    override fun onBrowseInteraction(projectId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBookmarkInteraction(projectId: String) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
