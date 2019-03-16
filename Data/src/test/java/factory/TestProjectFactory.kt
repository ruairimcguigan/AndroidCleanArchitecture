package factory

import com.example.data.model.ProjectEntity
import com.example.domain.model.Project

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
}