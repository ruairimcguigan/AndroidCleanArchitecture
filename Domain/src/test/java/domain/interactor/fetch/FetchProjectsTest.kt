package domain.interactor.fetch

import com.example.domain.executor.PostExecutionThread
import com.example.domain.interactor.fetch.FetchProjects
import com.example.domain.model.Project
import com.example.domain.repository.ProjectRepository
import com.nhaarman.mockitokotlin2.whenever
import domain.testdata.TestDataFactory
import domain.testdata.TestDataFactory.stubFetchProjects
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class FetchProjectsTest {

    private lateinit var fetchProjects: FetchProjects

    @Mock private lateinit var projectRepository: ProjectRepository
    @Mock private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        initMocks(this)
        fetchProjects = FetchProjects(projectRepository, postExecutionThread)
    }

    @Test
    fun `test fetchProjects completes successfully`() {
        // given
        stubFetchProjects(Observable.just(TestDataFactory.makeProjectList(2)), projectRepository)

        // when
        val testObserver = fetchProjects.buildUseCaseObservable().test()

        // then
        testObserver.assertComplete()
    }

    @Test
    fun `test fetchProjects returns data`() {
        // given
        val projectList = TestDataFactory.makeProjectList(2)
        stubFetchProjects(Observable.just(projectList), projectRepository)

        // when
        val testObserver = fetchProjects.buildUseCaseObservable().test()

        // then
        testObserver.assertValue(projectList)
    }
}