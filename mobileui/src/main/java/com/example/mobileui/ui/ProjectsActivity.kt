package com.example.mobileui.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.mobileui.R
import com.example.mobileui.ui.bookmarked.BookmarkedProjectsFragment
import com.example.mobileui.ui.browse.BrowseProjectsFragment
import dagger.android.AndroidInjection

class ProjectsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        setContentView(R.layout.projects_activity)
        initToolbar()

        if (savedInstanceState == null) {
            navigateTo(BrowseProjectsFragment.newInstance())
        }
    }

    private fun initToolbar() {
        setSupportActionBar(findViewById(R.id.toolbar))
        supportActionBar!!.run {
            setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_bookmarked -> {
                navigateTo(BookmarkedProjectsFragment.newInstance())
                true
            }
            android.R.id.home -> {
                navigateTo(BrowseProjectsFragment.newInstance())
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    public fun toggleHomeUp(show: Boolean) {
        supportActionBar!!.setDisplayHomeAsUpEnabled(show)
    }

    private fun navigateTo(frag: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.contentFrame, frag)
            .commitNow()
    }
}
