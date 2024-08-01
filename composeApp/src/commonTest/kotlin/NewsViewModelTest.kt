import CacheManager.CacheManager
import Model.Article
import Model.NewsResponse
import Model.Source
import Repository.NewsRepository
import Service.NewsSource
import Usecase.GetNewsUseCase
import ViewModel.NewsViewModel
import org.koin.test.KoinTest
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.koin.core.context.startKoin
import org.koin.test.get
import org.koin.dsl.module
import org.koin.core.module.Module
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.Test
import kotlin.test.assertTrue
import org.koin.core.context.stopKoin
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest

@OptIn(ExperimentalCoroutinesApi::class)
class NewsViewModelTest : KoinTest {

    private lateinit var viewModel: NewsViewModel

    // Koin module
    private val testModule = module {
        single<NewsSource> { FakeNewsSource() } // Use a fake NewsSource
        single<CacheManager> { FakeCacheManager() } // Use a fake CacheManager
        single { NewsRepository(get(), get()) } // Pass the fake implementations to NewsRepository
        single { GetNewsUseCase(get()) } // Pass the NewsRepository to GetNewsUseCase
        single { NewsViewModel(get()) } // Pass the GetNewsUseCase to NewsViewModel
    }

    @BeforeTest
    fun setUp() {
        // Start Koin
        startKoin {
            modules(testModule)
        }
        viewModel = get() // Get the ViewModel instance

        // Set the main dispatcher for testing
        Dispatchers.setMain(StandardTestDispatcher())
    }

    @Test
    fun testFetchNewsUpdatesStateWhenAPICallIsSuccessful() = runTest {
        // Arrange
        val apiKey = ""

        // Act
        viewModel.fetchNews(apiKey)

        // Run current tasks
        runCurrent()

        // Assert
        val result = viewModel.news.first()
        assertTrue(result.isNotEmpty(), "Expected articles to be fetched but got an empty list.")
    }

    @Test
    fun testFetchNewsResetsStateWhenAPICallFails() = runTest {
        // Arrange
        val apiKey = ""
        (get<NewsSource>() as FakeNewsSource).setShouldReturnError(true)

        // Act
        viewModel.fetchNews(apiKey)

        // Run current tasks
        runCurrent()

        // Assert
        val result = viewModel.news.first()
        assertTrue(result.isEmpty(), "Expected an empty list due to API failure but got ${result.size} items.")
    }

    @AfterTest
    fun tearDown() {
        // Stop Koin
        stopKoin()
        Dispatchers.resetMain()
    }

    // Fake implementations for testing

    class FakeNewsSource : NewsSource {
        private var shouldReturnError = false

        fun setShouldReturnError(value: Boolean) {
            shouldReturnError = value
        }

        override suspend fun fetchNews(apiKey: String): NewsResponse {
            return if (shouldReturnError) {
                throw Exception("Network Error")
            } else {
                NewsResponse(
                    status = "OK",
                    totalResults = 1,
                    articles = listOf(
                        Article(
                            source = Source(id = "1", name = "Example Source"),
                            author = "Author Name",
                            title = "Sample Title",
                            description = "Sample Description",
                            url = "https://example.com",
                            urlToImage = "https://example.com/image.jpg",
                            publishedAt = "2024-01-01T00:00:00Z",
                            content = "Sample Content"
                        )
                    )
                )
            }
        }
    }

    class FakeCacheManager : CacheManager {
        private var cachedNews: NewsResponse? = null

        override fun cacheNews(newsResponse: NewsResponse) {
            cachedNews = newsResponse
        }

        override fun getCachedNews(): NewsResponse? {
            return cachedNews
        }
    }
}