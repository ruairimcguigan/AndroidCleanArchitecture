package com.example.data.factory

import com.example.data.model.ProjectEntity
import com.example.domain.model.Project
import kotlin.test.assertEquals

object TestProjectFactory {

    fun makeProject(): Project {
        return Project(
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomBoolean()
        )
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomUuid(),
            TestDataFactory.randomBoolean()
        )
    }

    fun assertEqualData(entity: ProjectEntity, model: Project) {
        assertEquals(entity.id, model.id)
        assertEquals(entity.name, model.name)
        assertEquals(entity.fullName, model.fullName)
        assertEquals(entity.dateCreated, model.dateCreated)
        assertEquals(entity.startCount, model.startCount)
        assertEquals(entity.ownerAvatar, model.ownerAvatar)
        assertEquals(entity.ownerName, model.ownerName)
        assertEquals(entity.isBookmarked, model.isBookmarked)

    }
}