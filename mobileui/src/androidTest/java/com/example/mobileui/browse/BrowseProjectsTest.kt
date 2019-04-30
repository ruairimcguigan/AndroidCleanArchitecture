package com.example.mobileui.browse

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.rule.ActivityTestRule
import androidx.test.runner.AndroidJUnit4
import com.example.domain.model.Project
import com.example.mobileui.R
import com.example.mobileui.TestApp
import com.example.mobileui.testutil.TestProjectDataFactory.makeProject
import com.example.mobileui.ui.ProjectsActivity
import com.example.mobileui.ui.browse.BrowseProjectViewHolder
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class BrowseProjectsTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule<ProjectsActivity>(
        ProjectsActivity::class.java,
        false,
        false)

    @Test
    fun testActivityLaunches() {
        stubProjectsRepo_FetchProjects(
            Observable.just(listOf(makeProject())))

        activity.launchActivity(null)
    }

    @Test
    fun projectsDisplay() {

        // given
        val projects = listOf(
            makeProject(),
            makeProject(),
            makeProject())

        stubProjectsRepo_FetchProjects(Observable.just(projects))

        // when
        activity.launchActivity(null)

        // then
        projects.forEachIndexed { index, project ->
            onView(withId(R.id.projectsList))
                .perform(RecyclerViewActions.scrollToPosition<BrowseProjectViewHolder>(index))

            onView(withId(R.id.projectsList))
                .check(matches(hasDescendant(withText(project.fullName))))
        }
    }

    private fun stubProjectsRepo_FetchProjects(observable: Observable<List<Project>>) {
        whenever(TestApp.appComponent().projectsRepository().fetchProjects())
            .thenReturn(observable)
    }
}