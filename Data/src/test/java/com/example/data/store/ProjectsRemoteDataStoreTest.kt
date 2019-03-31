package com.example.data.store

import com.example.data.factory.TestDataFactory.randomUuid
import com.example.data.factory.TestProjectFactory.makeProjectEntity
import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsRemote
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class ProjectsRemoteDataStoreTest {

    @Mock private lateinit var store: ProjectsRemote

    private lateinit var remote: ProjectsRemoteDataStore

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        remote = ProjectsRemoteDataStore(store)
    }

    @Test
    fun `test fetch projects from remote source completes successfully`() {
        // given
        stubFetchProjectsFromRemote(Observable.just(listOf(makeProjectEntity())))

        // when
        val testObserver = store.fetchProjects().test()

        // then
        testObserver.assertComplete()
    }


    @Test
    fun `test fetch projects from remote source returns projects`() {
        // given
        val projects = listOf(makeProjectEntity())
        stubFetchProjectsFromRemote(Observable.just(projects))

        // when
        val testObserver = store.fetchProjects().test()

        // then
        testObserver.assertValue(projects)
    }

    private fun stubFetchProjectsFromRemote(observable: Observable<List<ProjectEntity>>) {
        whenever(remote.fetchProjects())
            .thenReturn(observable)
    }

    @Test (expected = UnsupportedOperationException::class)
    fun `test saveProjects throws unsupported exception`() {
        remote.saveProjects(listOf(makeProjectEntity())).test()
    }

    @Test (expected = UnsupportedOperationException::class)
    fun `test clearProjects throws unsupported exception`() {
        remote.clearProjects().test()
    }

    @Test (expected = UnsupportedOperationException::class)
    fun `test fetchBookmarkedProjects throws unsupported exception`() {
        remote.fetchBookmarkedProjects().test()
    }

    @Test (expected = UnsupportedOperationException::class)
    fun `test setProjectAsBookmarked throws unsupported exception`() {
        remote.setProjectAsBookmarked(randomUuid()).test()
    }

    @Test (expected = UnsupportedOperationException::class)
    fun `test setProjectAsUnbookmarked throws unsupported exception`() {
        remote.setProjectAsNotBookmarked(randomUuid()).test()
    }
}