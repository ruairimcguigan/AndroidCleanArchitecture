package com.example.data

import com.example.data.factory.TestDataFactory.randomUuid
import com.example.data.factory.TestProjectFactory.makeProject
import com.example.data.factory.TestProjectFactory.makeProjectEntity
import com.example.data.mapper.ProjectMapper
import com.example.data.model.ProjectEntity
import com.example.data.repository.ProjectsCache
import com.example.data.repository.ProjectsDataStore
import com.example.data.store.ProjectsDataStoreFactory
import com.example.domain.model.Project
import com.nhaarman.mockito_kotlin.any
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single
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
        stubFactoryGetCacheDataStore()
        stubAreProjectsCached(Single.just(false))
        stubIsCacheExpired(Single.just(false))
        stubSaveProjects(Completable.complete())
    }

    @Test
    fun `test fetchProjects() completes`() {
        // given
        stubFetchProjects(Observable.just(listOf(makeProjectEntity())))
        stubMapper(makeProject(), any())

        // when
        val testObserver = repository.fetchProjects().test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun `test fetch projects returns projects`() {
        // given
        val projectEntity = makeProjectEntity()
        val project = makeProject()

        stubFetchProjects(Observable.just(listOf(projectEntity)))
        stubMapper(project, projectEntity)

        // when
        val testObserver = repository.fetchProjects().test()

        // then
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun `test fetchBookmarkedProjects() completes`() {
        // given
        stubFetchBookmarkedProjects(Observable.just(listOf(makeProjectEntity())))
        stubMapper(makeProject(), any())

        // when
        val testObserver = repository.fetchBookmarkedProjects().test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun `test fetchBookmarked projects returns bookmarked projects`() {
        // given
        val projectEntity = makeProjectEntity()
        val project = makeProject()

        stubFetchBookmarkedProjects(Observable.just(listOf(projectEntity)))
        stubMapper(project, projectEntity)

        // when
        val testObserver = repository.fetchBookmarkedProjects().test()

        // then
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun `test bookmark project completes successfully`() {
        // given
        stubBookmarkProject(Completable.complete())

        // when
        val testObserver = repository.bookmarkProject(randomUuid()).test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun `test unbookmark project completes successfully`() {
        // given
        stubUnbookmarkProject(Completable.complete())

        // when
        val testObserver = repository.unbookmarkProject(randomUuid()).test()

        // then
        testObserver.assertComplete()
    }

    private fun stubMapper(model: Project, entity: ProjectEntity) {
        whenever(mapper.mapFromEntity(entity))
            .thenReturn(model)
    }

    private fun stubFactoryGetDataStore() {
        whenever(factory.getDataStore(any(), any())).thenReturn(store)
    }

    private fun stubFetchProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.fetchProjects()).thenReturn(observable)
    }

    private fun stubFetchBookmarkedProjects(observable: Observable<List<ProjectEntity>>) {
        whenever(store.fetchBookmarkedProjects())
            .thenReturn(observable)
    }

    private fun stubIsCacheExpired(single: Single<Boolean>) {
        whenever(cache.isProjectCacheExpired())
            .thenReturn(single)
    }

    private fun stubAreProjectsCached(single: Single<Boolean>) {
        whenever(cache.areProjectsCached())
            .thenReturn(single)
    }

    private fun stubFactoryGetCacheDataStore() {
        whenever(factory.getCacheDataStore()).thenReturn(store)
    }

    private fun stubSaveProjects(completable: Completable){
        whenever(store.saveProjects(any()))
            .thenReturn(completable)
    }

    private fun stubBookmarkProject(completable: Completable){
        whenever(store.setProjectAsBookmarked(any()))
            .thenReturn(completable)
    }

    private fun stubUnbookmarkProject(completable: Completable){
        whenever(store.setProjectAsNotBookmarked(any()))
            .thenReturn(completable)
    }
}