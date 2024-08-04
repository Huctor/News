@file:OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
package IosCacheManager

import CacheManager.CacheManager
import Model.NewsResponse
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import platform.Foundation.NSCachesDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSSearchPathForDirectoriesInDomains
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.NSUserDomainMask
import platform.Foundation.create
import platform.Foundation.writeToFile

/**
 * IosCacheManager is an implementation of CacheManager for iOS.
 */
@OptIn(kotlinx.cinterop.BetaInteropApi::class)
class IosCacheManager : CacheManager {

    private val cacheFileName = "news_cache.json"

    /**
     * Caches the provided news response by writing it to a file in the cache directory.
     *
     * @param newsResponse The news response to cache.
     */
    override fun cacheNews(newsResponse: NewsResponse) {
        val cacheDir = NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, true).firstOrNull() as String
        val cacheFilePath = cacheDir + "/" + cacheFileName
        val jsonString = Json.encodeToString(newsResponse)
        NSString.create(string = jsonString).writeToFile(cacheFilePath, atomically = true, encoding = NSUTF8StringEncoding, error = null)
    }

    /**
     * Retrieves the cached news response from the cache directory.
     *
     * @return The cached NewsResponse object, or null if no cached data is available.
     */
    override fun getCachedNews(): NewsResponse? {
        val cacheDir = NSSearchPathForDirectoriesInDomains(NSCachesDirectory, NSUserDomainMask, true).firstOrNull() as String
        val cacheFilePath = cacheDir + "/" + cacheFileName
        val fileManager = NSFileManager.defaultManager()
        return if (fileManager.fileExistsAtPath(cacheFilePath)) {
            val jsonString = NSString.create(contentsOfFile = cacheFilePath, encoding = NSUTF8StringEncoding, error = null) as String
            Json.decodeFromString(jsonString)
        } else {
            null
        }
    }
}
