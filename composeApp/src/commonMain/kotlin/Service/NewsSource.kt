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

    /**
     * Fetches stock data from the specified stock market source.
     *
     * @param apiKey The API key required for authentication with the stock API.
     * @return A StocksResponse object containing the fetched stock data.
     * @throws Exception If there is an error while fetching the stock data.
     */
    // suspend fun fetchStocks(apiKey: String): StocksResponse
}