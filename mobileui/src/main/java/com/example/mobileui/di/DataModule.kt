package com.example.mobileui.di

import com.example.data.ProjectDataRepository
import com.example.domain.repository.ProjectRepository
import dagger.Binds
import dagger.Module

@Module
abstract class DataModule {

    @Binds
    abstract fun bindDataRepo(dataRepo: ProjectDataRepository): ProjectRepository
}