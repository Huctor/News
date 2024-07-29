package Service

import Model.NewsResponse
import io.ktor.client.*
import io.ktor.client.call.body
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.HttpStatusCode

/**
 * NewsApiService is responsible for making API calls to fetch news data.
 *
 * @param client The HTTP client used to make requests to the news API.
 */
class NewsApiService(private val client: HttpClient):NewsSource {

    /**
     * Fetches the top headlines from the news API using the provided API key.
     *
     * @param apiKey The API key required for authentication with the news API.
     * @return A NewsResponse object containing the fetched news data.
     * @throws Exception If there is an error while fetching the news or if the response status is not OK.
     */
    override suspend fun fetchNews(apiKey: String): NewsResponse {

        try {
            // Make a GET request to the news API endpoint
            val response: HttpResponse = client.get("https://newsapi.org/v2/top-headlines?country=us&apiKey=" + apiKey)

            // Check if the response status is OK (200)
            if (response.status == HttpStatusCode.OK) {
                // Deserialize the response body into a NewsResponse object and return it
                return response.body()
            } else {
                // Throw an exception if the response status is not OK
                throw Exception("Error: ${response.status}")
            }
        } catch (e: Exception) {
            // Handle exceptions and logging here
            throw Exception("Failed to fetch news: ${e.message}")
        }
    }
}