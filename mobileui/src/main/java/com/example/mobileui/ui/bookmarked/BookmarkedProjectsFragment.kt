package com.example.mobileui.ui.bookmarked

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.mobileui.R

class BookmarkedProjectsFragment : Fragment() {
    private var listener: BookmarkListener? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        val view = ConstraintLayout(activity)
        inflater.inflate(R.layout.project_list, view, true)
        return view
    }

    fun unbookmarkProject(projectId: String) {
        listener?.onBookmarkInteraction(projectId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BookmarkListener) {
            listener = context
        } else {
            throw RuntimeException("$context must implement BookmarkListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface BookmarkListener {
        fun onBookmarkInteraction(projectId: String)
    }
}
