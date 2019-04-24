package com.example.mobileui.ui.browseprojects

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mobileui.R

class BrowseProjectsFragment : Fragment() {

    companion object {
        fun newInstance() = BrowseProjectsFragment()
    }

    private lateinit var viewModel: BrowseProjectsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.browse_projects_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(BrowseProjectsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
