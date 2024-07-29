package CacheManager

import Model.NewsResponse
/**
 * Interface representing a cache manager for news data.
 */
interface CacheManager {
    /**
     * Caches the given news response.
     *
     * @param newsResponse The news response to cache.
     */
    fun cacheNews(newsResponse: NewsResponse)

    /**
     * Retrieves the cached news response, if available.
     *
     * @return The cached news response, or null if no cache is available.
     */
    fun getCachedNews(): NewsResponse?
}

