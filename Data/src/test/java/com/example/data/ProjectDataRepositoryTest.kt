package com.example.data

import com.example.data.factory.TestStubFactory.stubFactoryGetDataStore
import com.example.data.mapper.ProjectMapper
import com.example.data.repository.ProjectsCache
import com.example.data.repository.ProjectsDataStore
import com.example.data.store.ProjectsDataStoreFactory
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class ProjectDataRepositoryTest {

    private lateinit var repository: ProjectDataRepository

    @Mock private lateinit var mapper: ProjectMapper
    @Mock private lateinit var cache:ProjectsCache
    @Mock private lateinit var factory: ProjectsDataStoreFactory
    @Mock private lateinit var store: ProjectsDataStore

    @Before
    fun setUp() {
        initMocks(this)
        repository = ProjectDataRepository(mapper, cache, factory)

        stubFactoryGetDataStore()
    }

    @Test
    fun `test fetchProjects() completes`() {

    }
}