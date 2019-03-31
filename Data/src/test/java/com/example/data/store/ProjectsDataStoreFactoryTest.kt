package com.example.data.store

import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.MockitoAnnotations.*
import kotlin.test.assertEquals

class ProjectsDataStoreFactoryTest {

    private lateinit var factory: ProjectsDataStoreFactory

    @Mock private lateinit var cacheDataStore: ProjectsCacheDataStore
    @Mock private lateinit var remoteDataStore: ProjectsRemoteDataStore

    @Before
    fun setUp() {
        initMocks(this)
        factory = ProjectsDataStoreFactory(cacheDataStore, remoteDataStore)
    }

    @Test
    fun `getDataStore() returns remoteDataStore when cache has expired`() {
        assertEquals(remoteDataStore, factory.getDataStore(true, true))
    }

    @Test
    fun `getDataStore() returns remote when no projects are cached`() {
        assertEquals(remoteDataStore, factory.getDataStore(false, false))
    }

    @Test
    fun `getDataStore() returns cacheDataStore when cache has not expired`() {
        assertEquals(cacheDataStore, factory.getDataStore(true, false))
    }

    @Test
    fun `getCacheDataStore() returns cacheStore`() {
        assertEquals(cacheDataStore, factory.getCacheDataStore())
    }

    @Test
    fun `getRemoteDataStore() returns remoteStore`() {
        assertEquals(remoteDataStore, factory.getRemoteDataStore())
    }
}