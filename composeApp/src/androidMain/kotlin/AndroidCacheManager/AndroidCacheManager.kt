package AndroidCacheManager

import CacheManager.CacheManager
import Model.NewsResponse
import android.content.Context
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import java.io.File

/**
 * AndroidCacheManager is an implementation of CacheManager for Android.
 *
 * @param context The application context used to access the cache directory.
 */
class AndroidCacheManager(private val context: Context) : CacheManager {

    private val cacheFile = File(context.cacheDir, "news_cache.json")

    /**
     * Caches the provided news response by writing it to a file in the cache directory.
     *
     * @param newsResponse The news response to cache.
     */
    override fun cacheNews(newsResponse: NewsResponse) {
        cacheFile.writeText(Json.encodeToString(newsResponse))
    }

    /**
     * Retrieves the cached news response from the cache directory.
     *
     * @return The cached NewsResponse object, or null if no cached data is available.
     */
    override fun getCachedNews(): NewsResponse? {
        return if (cacheFile.exists()) {
            val cachedText = cacheFile.readText()
            return Json.decodeFromString(cachedText)
        } else {
            return null
        }
    }
}