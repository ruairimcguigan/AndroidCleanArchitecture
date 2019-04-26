package com.example.mobileui.di

import com.example.mobileui.ui.bookmarked.BookmarkedProjectsFragment
import com.example.mobileui.ui.browse.BrowseProjectsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilderModule {

    @ContributesAndroidInjector
    internal abstract fun contributeBrowseFragment(): BrowseProjectsFragment

    @ContributesAndroidInjector
    internal abstract fun contributeBookmarkedFragment(): BookmarkedProjectsFragment
}