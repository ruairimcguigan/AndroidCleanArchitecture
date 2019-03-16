package com.example.data.factory

import com.example.data.mapper.ProjectMapper
import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsCache
import com.example.data.repository.ProjectsDataStore
import com.example.data.store.ProjectsDataStoreFactory
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.mockito.Mock

object TestStubFactory {

    @Mock private lateinit var mapper: ProjectMapper
    @Mock private lateinit var cache: ProjectsCache
    @Mock private lateinit var factory: ProjectsDataStoreFactory
    @Mock private lateinit var store: ProjectsDataStore

    fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any())).thenReturn(store)
    }

    fun stubFetchProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.fetchProjects()).thenReturn(observable)
    }

}