package com.example.data.store

import com.example.data.factory.TestDataFactory.randomUuid
import com.example.data.factory.TestProjectFactory.makeProjectEntity
import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsCache
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.verify
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class ProjectsCacheDataStoreTest {

    @Mock private lateinit var cache: ProjectsCache

    private lateinit var store: ProjectsCacheDataStore

    @Before
    fun setUp() {
        initMocks(this)
        store = ProjectsCacheDataStore(cache)
    }

    @Test
    fun `test fetchProjects completes successfully`() {
        // given
        stubProjectsCacheFetchProjects(Observable.just(listOf(makeProjectEntity())))

        // when
        val testObserver = store.fetchProjects().test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun `test fetchProjects returns projects`() {
        // given
        val projects = listOf(makeProjectEntity())
        stubProjectsCacheFetchProjects(Observable.just(projects))

        // when
        val testObserver = store.fetchProjects().test()

        // then
        testObserver.assertValue(projects)
    }

    @Test
    fun `test save projects completes successfully and returns data`() {
        // given
        val projects = listOf(makeProjectEntity())
        stubSaveProjects(Completable.complete())
        stubProjectsCacheSetLastCacheTime(Completable.complete())

        // when
        val testObserver = store.saveProjects(listOf(makeProjectEntity())).test()
        store.saveProjects(projects).test()

        // then
        testObserver.assertComplete()
        verify(cache).saveProjects(projects)
    }

    @Test
    fun `test clear projects completed successfully`() {
        // given
        stubClearProjects(Completable.complete())

        // when
        val testObserver = cache.clearProjects().test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun `test getBookmarkedProjects completes successfully`() {
        // given
        stubFetchBookmarkedProjects(Observable.just(listOf(makeProjectEntity())))

        // when
        val testObserver = cache.getBookmarkedProjects().test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun `test getBookmarkedProjects returns bookmarked projects`() {
        // given
        val projects = listOf(makeProjectEntity())
        stubFetchBookmarkedProjects(Observable.just(projects))

        // when
        val testObserver = cache.getBookmarkedProjects().test()

        // then
        testObserver.assertValue(projects)
    }

    @Test
    fun `test setProjectAsBookmarked completes successfully`() {
        // given
        stubSetProjectAsBookmarked(Completable.complete())

        // when
        val testObserver = cache.bookmarkProject(randomUuid()).test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun `test setProject as not bookmarked completes successfully`() {
        // given
        stubSetProjectAsUnbookmarked(Completable.complete())

        // when
        val testObserver = cache.unbookmarkProject(randomUuid()).test()

        // then
        testObserver.assertComplete()
    }

    private fun stubClearProjects(completable: Completable) {
        whenever(cache.clearProjects())
            .thenReturn(completable)
    }

    private fun stubProjectsCacheFetchProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.fetchProjects())
            .thenReturn(observable)
    }

    private fun stubSaveProjects(completable: Completable) {
        whenever(cache.saveProjects(any()))
            .thenReturn(completable)
    }

    private fun stubProjectsCacheSetLastCacheTime(completable: Completable) {
        whenever(cache.setLastCacheTime(any()))
            .thenReturn(completable)
    }

    private fun stubFetchBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(cache.getBookmarkedProjects())
            .thenReturn(observable)
    }

    private fun stubSetProjectAsBookmarked(completable: Completable) {
        whenever(cache.bookmarkProject(any()))
            .thenReturn(completable)
    }

    private fun stubSetProjectAsUnbookmarked(completable: Completable) {
        whenever(cache.unbookmarkProject(any()))
            .thenReturn(completable)
    }
}