package com.example.remote.factory

import com.example.data.model.ProjectEntity
import com.example.remote.factory.TestDataFactory.randomBoolean
import com.example.remote.factory.TestDataFactory.randomInt
import com.example.remote.factory.TestDataFactory.randomUuid
import com.example.remote.model.OwnerModel
import com.example.remote.model.ProjectModel
import com.example.remote.model.ProjectsResponseModel

object TestProjectDataFactory {

    fun makeOwner(): OwnerModel {
        return OwnerModel(randomUuid(), randomUuid())
    }

    fun makeProjectModel(): ProjectModel {
        return ProjectModel(
            randomUuid(),
            randomUuid(),
            randomUuid(),
            randomInt(),
            randomUuid(),
            makeOwner())
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
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

    fun makeProjectsResponse(): ProjectsResponseModel {
        return ProjectsResponseModel(listOf(
            makeProjectModel(),
            makeProjectModel()))
    }
}