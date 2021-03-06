package domain.testdata

import com.example.domain.model.Project
import com.example.domain.repository.ProjectRepository
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Completable
import io.reactivex.Observable
import org.mockito.ArgumentMatchers.any
import java.util.*

object TestDataFactory {

    fun randomUuid(): String {
        return UUID.randomUUID().toString()
    }

    fun randomBoolean(): Boolean {
        return Math.random() < 0.5
    }

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

    fun makeProjectList(count: Int): List<Project> {

        val projects = mutableListOf<Project>()
        repeat(count) {
            projects.add(makeProject())
        }
        return projects
    }

     fun stubFetchProjects(observable: Observable<List<Project>>, projectRepository: ProjectRepository){
        whenever(projectRepository.fetchProjects()).thenReturn(observable)
    }

    fun stubFetchBookmarkedProjects(observable: Observable<List<Project>>, projectRepository: ProjectRepository){
        whenever(projectRepository.fetchBookmarkedProjects()).thenReturn(observable)
    }

    fun stubBookmarkedProject(completable: Completable, projectRepository: ProjectRepository){
        whenever(projectRepository.bookmarkProject("id")).thenReturn(completable)
    }

    fun stubUnBookmarkedProject(completable: Completable, projectRepository: ProjectRepository){
        whenever(projectRepository.unbookmarkProject("id")).thenReturn(completable)
    }
}