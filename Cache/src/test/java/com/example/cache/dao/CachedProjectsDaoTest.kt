package com.example.cache.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import com.example.cache.db.ProjectsDb
import com.example.cache.factory.TestProjectFactory.makeBookmarkedCachedProject
import com.example.cache.factory.TestProjectFactory.makeCachedProject
import org.junit.After
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment.application

@RunWith(RobolectricTestRunner::class)
class CachedProjectsDaoTest {

    @Rule
    @JvmField var instantTaskExecutorRule = InstantTaskExecutorRule()

    private val database = Room.inMemoryDatabaseBuilder(
        application.applicationContext,
        ProjectsDb::class.java)
        .allowMainThreadQueries()
        .build()

    @After
    fun closeDB(){
        database.close()
    }

    @Test
    @Ignore
    fun `test that fetchProjects() returns data`() {
        // given
        val project = makeCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project))

        // when
        val testObserver = database.cachedProjectsDao().fetchProjects().test()

        // then
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun `test that deleteProjects() clears stored data`() {
        // given
        val project = makeCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project))
        database.cachedProjectsDao().deleteProjects()

        // when
        val testObserver = database.cachedProjectsDao().fetchProjects().test()

        // then
        testObserver.assertValue(emptyList())
    }

    @Test
    fun deleteProjectsClearsData() {
        val project = makeCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project))
        database.cachedProjectsDao().deleteProjects()

        val testObserver = database.cachedProjectsDao().fetchProjects().test()
        testObserver.assertValue(emptyList())
    }

    @Test
    @Ignore
    fun getBookmarkedProjectsReturnsData() {
        val project = makeCachedProject()
        val bookmarkedProject = makeBookmarkedCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project, bookmarkedProject))

        val testObserver = database.cachedProjectsDao().getBookmarkedProjects().test()
        testObserver.assertValue(listOf(bookmarkedProject))
    }

    @Test
    @Ignore
    fun setProjectAsBookmarkedSavesData() {
        val project = makeCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project))
        database.cachedProjectsDao().setBookmarkStatus(true, project.id)
        project.isBookmarked = true

        val testObserver = database.cachedProjectsDao().getBookmarkedProjects().test()
        testObserver.assertValue(listOf(project))
    }

    @Test
    fun setProjectAsNotBookmarkedSavesData() {
        val project = makeBookmarkedCachedProject()
        database.cachedProjectsDao().insertProjects(listOf(project))
        database.cachedProjectsDao().setBookmarkStatus(false, project.id)
        project.isBookmarked = false

        val testObserver = database.cachedProjectsDao().getBookmarkedProjects().test()
        testObserver.assertValue(emptyList())
    }
}