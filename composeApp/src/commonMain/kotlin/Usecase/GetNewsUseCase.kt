package Usecase

import Model.NewsResponse
import Repository.NewsRepository

/**
 * GetNewsUseCase is responsible for retrieving news using the NewsRepository.
 *
 * @param newsRepository The repository used to fetch news data from the API.
 */
class GetNewsUseCase(private val newsRepository: NewsRepository) {

    /**
     * Executes the use case to fetch news with the provided API key.
     *
     * @param apiKey The API key required for authentication with the news API.
     * @return A NewsResponse object containing the fetched news data.
     */
    suspend fun execute(apiKey: String): NewsResponse {
        // Call the repository to get the news using the provided API key
        return newsRepository.getNews(apiKey)
    }
}