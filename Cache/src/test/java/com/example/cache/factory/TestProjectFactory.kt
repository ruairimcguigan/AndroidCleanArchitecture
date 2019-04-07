package com.example.cache.factory

import com.example.cache.factory.TestDataFactory.randomBoolean
import com.example.cache.factory.TestDataFactory.randomUuid
import com.example.cache.model.CachedProject
import com.example.data.model.ProjectEntity

object TestProjectFactory {
    fun makeCachedProject(): CachedProject {
        return CachedProject(
            randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            false
        )
    }

    fun makeBookmarkedCachedProject(): CachedProject {
        return CachedProject(
            randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            true
        )
    }

    fun makeProjectEntity(): ProjectEntity {
        return ProjectEntity(
            randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            randomBoolean()
        )
    }

    fun makeBookmarkedProjectEntity(): ProjectEntity {
        return ProjectEntity(
            randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            randomUuid(), randomUuid(),
            true
        )
    }
}