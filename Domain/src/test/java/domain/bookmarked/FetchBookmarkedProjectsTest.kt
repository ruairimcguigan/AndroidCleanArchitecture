package domain.bookmarked

import com.example.domain.executor.PostExecutionThread
import com.example.domain.interactor.bookmarked.FetchBookmarkedProjects
import com.example.domain.repository.ProjectRepository
import domain.testdata.TestDataFactory.makeProjectList
import domain.testdata.TestDataFactory.stubFetchBookmarkedProjects
import io.reactivex.Observable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class FetchBookmarkedProjectsTest {

    private lateinit var fetchBookmarkedProjects: FetchBookmarkedProjects

    @Mock private lateinit var projectRepository: ProjectRepository
    @Mock private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        initMocks(this)
        fetchBookmarkedProjects = FetchBookmarkedProjects(projectRepository, postExecutionThread)
    }

    @Test
    fun `test FetchBookmarkedProjects completes successfully`() {
        // given
        stubFetchBookmarkedProjects(Observable.just(makeProjectList(2)), projectRepository)

        // then
        val testObserver = fetchBookmarkedProjects.buildUseCaseObservable().test()

        // when
        testObserver.assertComplete()
    }

    @Test
    fun `test FetchBookmarkedProjects returns data`() {
        // given
        val bookmarkedProjects = makeProjectList(2)
        stubFetchBookmarkedProjects(Observable.just(bookmarkedProjects), projectRepository)

        // then
        val testObserver = fetchBookmarkedProjects.buildUseCaseObservable().test()

        // when
        testObserver.assertValue(bookmarkedProjects)
    }
}