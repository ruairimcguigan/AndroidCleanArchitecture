package com.example.data.mapper

import com.example.data.factory.TestProjectFactory.assertEqualData
import com.example.data.factory.TestProjectFactory.makeProject
import com.example.data.factory.TestProjectFactory.makeProjectEntity
import org.junit.Test

class ProjectMapperTest {

    private val mapper = ProjectMapper()

    @Test
    fun `test mapFromEntity`() {
        val entity = makeProjectEntity()
        val model = mapper.mapFromEntity(entity)

        assertEqualData(entity, model)
    }

    @Test
    fun `test mapToEntity`() {
        val model = makeProject()
        val entity = mapper.mapToEntity(model)

        assertEqualData(entity, model)
    }
}