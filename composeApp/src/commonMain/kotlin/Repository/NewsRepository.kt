package Repository

import CacheManager.CacheManager
import Model.NewsResponse
import Service.NewsApiService
import Service.NewsSource

/**
 * NewsRepository is responsible for fetching news from the API.
 *
 * @param apiService The service used to make API calls to fetch news.
 */
class NewsRepository(private val newsSource: NewsSource, private val cacheManager: CacheManager) {
    /**
     * Fetches news from the API using the provided API key.
     *
     * @param apiKey The API key required for authentication with the news API.
     * @return A NewsResponse object containing the fetched news data.
     */
    suspend fun getNews(apiKey: String): NewsResponse {
        return try {
            // Fetch news from the API
            val newsResponse = newsSource.fetchNews(apiKey)
            // Cache the news response
            cacheManager.cacheNews(newsResponse)
            return newsResponse
        } catch (e: Exception) {
            // Log the error and attempt to load cached news
            println("Failed to fetch news from API: ${e.message}. Loading cached news.")
            // Return cached news if available, otherwise throw an exception
            val cacheData = cacheManager.getCachedNews()

            if(cacheData != null){
                return cacheData
            }else{
                throw Exception("No cached news available.")
            }
        }
    }
}