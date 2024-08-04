package DI

import AndroidCacheManager.AndroidCacheManager
import CacheManager.CacheManager
import CacheManager.SqlDelightCacheManager
import DB.DatabaseDriverFactory
import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.newsapp.database.NewsDatabase
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

/**
 * Creates and configures an HttpClient instance using the OkHttp engine.
 *
 * This function sets up an HttpClient with logging of HTTP requests and responses,
 * as well as configuring JSON serialization options.
 *
 * @return An instance of HttpClient configured with logging and content negotiation features.
 */
actual fun createHttpClient(): HttpClient {
    return HttpClient(OkHttp.create()) {
        // Install the Logging feature to log all HTTP requests and responses
        install(Logging) {
            level = LogLevel.ALL // Set the logging level to log everything
        }
        // Install the ContentNegotiation feature with JSON support
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true // Ignore unknown keys in the JSON response
                }
            )
        }
    }
}

/**
 * Android-specific Koin module to provide the AndroidCacheManager implementation.
 */
/* val androidModule = module {
    single<CacheManager> { AndroidCacheManager(get()) }
    single<SqlDriver> {
        AndroidSqliteDriver(NewsDatabase.Schema, get(), "NewsDatabase.db")
    }
}*/

fun androidModule(context: Context) = module {
    // single<CacheManager> { AndroidCacheManager(get()) }

    // Provide the Android-specific DatabaseDriverFactory
    single { DatabaseDriverFactory(context) }

    single<SqlDriver> {
        get<DatabaseDriverFactory>().create()
    }

    single<CacheManager> {
        SqlDelightCacheManager(get())
    }
}