package com.example.presentation.testdata

import com.example.domain.model.Project
import com.example.presentation.model.ProjectView

object TestProjectFactory {

    fun makeProjectView(): ProjectView {
        return ProjectView(TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomBoolean())
    }

    fun makeProject(): Project {
        return Project(TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomString(), TestDataFactory.randomString(),
            TestDataFactory.randomBoolean())
    }

    fun makeProjectViewList(count: Int): List<ProjectView> {
        val projects = mutableListOf<ProjectView>()
        repeat(count) {
            projects.add(makeProjectView())
        }
        return projects
    }

    fun makeProjectList(count: Int): List<Project> {
        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }
}