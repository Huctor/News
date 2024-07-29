package DI

import CacheManager.CacheManager
import IosCacheManager.IosCacheManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.darwin.Darwin
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

/**
 * Creates and configures an HttpClient instance with necessary features.
 *
 * This function sets up an HttpClient with the Darwin engine, enabling logging
 * of all HTTP requests and responses, as well as configuring JSON serialization
 * with specific options.
 *
 * @return An instance of HttpClient configured with logging and content negotiation features.
 */
actual fun createHttpClient(): HttpClient {
    return HttpClient(Darwin.create()) {
        // Install the Logging feature to log HTTP requests and responses
        install(Logging) {
            level = LogLevel.ALL
        }
        // Install the ContentNegotiation feature for handling JSON content
        install(ContentNegotiation) {
            json(
                json = Json {
                    ignoreUnknownKeys = true
                }
            )
        }
    }
}

/**
 * iOS-specific Koin module to provide the IosCacheManager implementation.
 */
val iosModule = module {
    single<CacheManager> { IosCacheManager() }
}