package Repository

import Model.NewsResponse
import Service.NewsApiService

/**
 * NewsRepository is responsible for fetching news from the API.
 *
 * @param apiService The service used to make API calls to fetch news.
 */
class NewsRepository(private val apiService: NewsApiService) {
    /**
     * Fetches news from the API using the provided API key.
     *
     * @param apiKey The API key required for authentication with the news API.
     * @return A NewsResponse object containing the fetched news data.
     */
    suspend fun getNews(apiKey: String): NewsResponse {
        return apiService.fetchNews(apiKey)
    }
}