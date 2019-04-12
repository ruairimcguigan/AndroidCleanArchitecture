package com.example.cache

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.example.cache.db.ProjectsDb
import com.example.cache.factory.TestProjectFactory.makeBookmarkedProjectEntity
import com.example.cache.factory.TestProjectFactory.makeProjectEntity
import com.example.cache.mapper.CachedProjectMapper
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment

@RunWith(RobolectricTestRunner::class)
class ProjectsCacheImplTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        RuntimeEnvironment.application.applicationContext,
        ProjectsDb::class.java)
        .allowMainThreadQueries()
        .build()
    private val entityMapper = CachedProjectMapper()
    private val cache = ProjectsCacheImpl(database, entityMapper)

    @Test
    fun clearTablesCompletes() {
        // when
        val testObserver = cache.clearProjects().test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun saveProjectsCompletes() {
        // given
        val projects = listOf(makeProjectEntity())

        // when
        val testObserver = cache.saveProjects(projects).test()

        // then
        testObserver.assertComplete()
    }

    @Test
    @Ignore
    fun getProjectsReturnsData() {
        // given
        val projects = listOf(makeProjectEntity())
        cache.saveProjects(projects).test()

        // when
        val testObserver = cache.fetchProjects().test()

        // then
        testObserver.assertValue(projects)
    }

    @Test
    @Ignore
    fun getBookmarkedProjectsReturnsData() {
        // given
        val bookmarkedProject = makeBookmarkedProjectEntity()
        val projects = listOf(makeProjectEntity(), bookmarkedProject)

        // when
        cache.saveProjects(projects).test()
        val testObserver = cache.getBookmarkedProjects().test()

        // then
        testObserver.assertValue(listOf(bookmarkedProject))
    }

    @Test
    fun setProjectAsBookmarkedCompletes() {
        // given
        val projects = listOf(makeProjectEntity())
        cache.saveProjects(projects).test()

        // when
        val testObserver = cache.bookmarkProject((projects[0].id)).test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun setProjectAsNotBookmarkedCompletes() {
        // given
        val projects = listOf(makeBookmarkedProjectEntity())
        cache.saveProjects(projects).test()

        // when
        val testObserver = cache.bookmarkProject(projects[0].id).test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun areProjectsCacheReturnsData() {
        // given
        val projects = listOf(makeProjectEntity())
        cache.saveProjects(projects).test()

        // when
        val testObserver = cache.areProjectsCached().test()

        // then
        testObserver.assertValue(true)
    }

    @Test
    fun setLastCacheTimeCompletes() {
        // when
        val testObserver = cache.setLastCacheTime(1000L).test()

        // then
        testObserver.assertComplete()
    }

    @Test
    @Ignore
    fun isProjectsCacheExpiredReturnsNotExpired() {

        // when
        cache.setLastCacheTime(1000L).test()
        val testObserver = cache.isProjectsCacheExpired().test()

        // then
        testObserver.assertValue(false)
    }

}
