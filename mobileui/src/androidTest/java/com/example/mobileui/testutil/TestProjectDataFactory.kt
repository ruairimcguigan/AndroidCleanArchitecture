package com.example.mobileui.testutil

import com.example.domain.model.Project
import com.example.mobileui.testutil.TestDataFactory.randomBoolean
import com.example.mobileui.testutil.TestDataFactory.randomUuid
import com.example.presentation.model.ProjectView

object TestProjectDataFactory {

    fun makeProject(): Project {
        return Project(
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomBoolean())
    }

    fun makeProjectView(): ProjectView {
        return ProjectView(
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomBoolean()
        )
    }
}