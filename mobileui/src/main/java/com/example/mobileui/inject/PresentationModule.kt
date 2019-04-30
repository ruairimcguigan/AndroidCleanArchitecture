package com.example.mobileui.inject

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.presentation.viewmodel.BookmarkedProjectsViewModel
import com.example.presentation.viewmodel.BrowseProjectsViewModel
import com.example.presentation.viewmodel.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PresentationModule {

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(BrowseProjectsViewModel::class)
    abstract fun bindBrowseProjectsViewModel(
        viewModel: BrowseProjectsViewModel
    ): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookmarkedProjectsViewModel::class)
    abstract fun bindBrowseBookmarkedProjectsViewModel(
        viewModel: BookmarkedProjectsViewModel
    ): ViewModel
}