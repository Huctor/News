package DI

import CacheManager.CacheManager
import CacheManager.SqlDelightCacheManager
import DB.DatabaseDriverFactory
import Repository.NewsRepository
import Service.NewsApiService
import Service.NewsSource
import Usecase.GetNewsUseCase
import ViewModel.NewsViewModel
import app.cash.sqldelight.db.SqlDriver
import com.newsapp.database.NewsDatabase
import io.ktor.client.HttpClient
import org.koin.dsl.module

/**
 * Expect declaration for a function that creates an HttpClient.
 *
 * This function is expected to be implemented in platform-specific code.
 * It provides a way to create an instance of HttpClient for making
 * HTTP requests in a multiplatform project.
 */
expect fun createHttpClient(): HttpClient

/**
 * Define a common Koin module for dependency injection.
 *
 * This function sets up the Koin dependency injection framework by
 * declaring singletons and factories for various components used in
 * the application, enabling easy access and management of dependencies.
 *
 * @return A Koin module containing the necessary declarations for the
 *         application's dependencies.
 */
fun commonModule() = module {
    // Declare a singleton for HttpClient using the platform-specific implementation
    single { createHttpClient() }
    // Declare a singleton for NewsApiService, injecting HttpClient
    single<NewsSource> { NewsApiService(get()) }
    // Declare a singleton for NewsRepository, injecting NewsApiService
    single { NewsRepository(get(), get()) }
    // Declare a singleton for GetNewsUseCase, injecting NewsRepository
    single { GetNewsUseCase(get()) }
    // Declare a factory for NewsViewModel, injecting GetNewsUseCase
    factory { NewsViewModel(get()) }

    // Provide MyDatabase
    single {
        NewsDatabase(get())
    }
    // Provide CacheManager
    single<CacheManager> {
        SqlDelightCacheManager(get())
    }

    // Provide SqlDriver using DatabaseDriverFactory
    single<SqlDriver> { get<DatabaseDriverFactory>().create() }

}