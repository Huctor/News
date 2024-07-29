package Service

import Model.NewsResponse

// Interface that represents a source for fetching news articles.
interface NewsSource {

    /**
     * Fetches news articles from the specified news source.
     *
     * @param apiKey The API key required for authentication with the news API.
     * @return A NewsResponse object containing the fetched news data.
     * @throws Exception If there is an error while fetching the news data.
     */
    suspend fun fetchNews(apiKey: String): NewsResponse
}