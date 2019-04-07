package domain.bookmarked

import com.example.domain.executor.PostExecutionThread
import com.example.domain.interactor.bookmarked.UnbookmarkProject
import com.example.domain.repository.ProjectRepository
import domain.testdata.TestDataFactory
import domain.testdata.TestDataFactory.stubUnBookmarkedProject
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class UnbookmarkProjectTest {

    private lateinit var unbookmarkProject: UnbookmarkProject

    @Mock
    private lateinit var projectRepository: ProjectRepository
    @Mock
    private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        unbookmarkProject = UnbookmarkProject(projectRepository, postExecutionThread)
    }

    @Test
    fun `test BookmarkProject completes successfully`() {
        // given
        stubUnBookmarkedProject(Completable.complete(), projectRepository)

        // when
        val testObserver = unbookmarkProject.buildUseCaseCompletable(
            UnbookmarkProject.Params.forProject("id")).test()

        // then
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test bookmarkProject throws an exception when no project id is passed`() {
        // then
        unbookmarkProject.buildUseCaseCompletable().test()
    }
}