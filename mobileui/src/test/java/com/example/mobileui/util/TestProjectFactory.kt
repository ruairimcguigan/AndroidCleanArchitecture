package com.example.mobileui.util

import com.example.mobileui.util.TestDataFactory.randomBoolean
import com.example.mobileui.util.TestDataFactory.randomUuid
import com.example.presentation.model.ProjectView

object ProjectFactory {

    fun makeProjectView(): ProjectView {
        return ProjectView(
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomBoolean())
    }

}