package com.example.mobileui.ui.browse

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import com.example.mobileui.R

class BrowseProjectsFragment : Fragment() {

    private var listener: BrowseListener? = null

    companion object {
        fun newInstance() = BrowseProjectsFragment()
    }

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        val view = ConstraintLayout(activity)
        inflater.inflate(R.layout.project_list, view, true)
        return view
    }

    fun bookmarkProject(projectId: String) {
        listener?.onBrowseInteraction(projectId)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BrowseListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement BookmarkListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    interface BrowseListener {
        fun onBrowseInteraction(projectId: String)
    }

}