package com.example.presentation.mapper

import com.example.presentation.testdata.TestProjectFactory
import org.junit.Assert.assertEquals
import org.junit.Test

class ProjectViewMapperTest {

    private val mapper = ProjectViewMapper()

    @Test
    fun mapToViewMapsData() {
        val project = TestProjectFactory.makeProject()
        val projectView = mapper.mapToView(project)

        assertEquals(project.id, projectView.id)
        assertEquals(project.name, projectView.name)
        assertEquals(project.fullName, projectView.fullName)
        assertEquals(project.starCount, projectView.starCount)
        assertEquals(project.dateCreated, projectView.dateCreated)
        assertEquals(project.ownerName, projectView.ownerName)
        assertEquals(project.ownerAvatar, projectView.ownerAvatar)
        assertEquals(project.isBookmarked, projectView.isBookmarked)
    }
}