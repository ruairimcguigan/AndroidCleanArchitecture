package domain.interactor.bookmarked

import com.example.domain.executor.PostExecutionThread
import com.example.domain.interactor.bookmarked.BookmarkProject
import com.example.domain.repository.ProjectRepository
import domain.testdata.TestDataFactory
import domain.testdata.TestDataFactory.stubBookmarkedProject
import io.reactivex.Completable
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations.initMocks

class BookmarkProjectTest {

    private lateinit var bookmarkProject: BookmarkProject

    @Mock private lateinit var projectRepository: ProjectRepository
    @Mock private lateinit var postExecutionThread: PostExecutionThread

    @Before
    fun setUp() {
        initMocks(this)
        bookmarkProject = BookmarkProject(projectRepository, postExecutionThread)
    }

    @Test
    fun `test BookmarkProject completes successfully`() {
        // given
        stubBookmarkedProject(Completable.complete(), projectRepository)

        // when
        val testObserver = bookmarkProject.buildUseCaseCompletable(
            BookmarkProject.Params.forProject(TestDataFactory.randomUuid())).test()

        // then
        testObserver.assertComplete()
    }

    @Test(expected = IllegalArgumentException::class)
    fun `test bookmarkProject throws an exception when no project id is passed`() {
        // then
        bookmarkProject.buildUseCaseCompletable().test()
    }
}