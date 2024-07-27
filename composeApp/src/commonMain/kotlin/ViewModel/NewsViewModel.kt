package ViewModel

import Model.Article
import Usecase.GetNewsUseCase
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

/**
 * NewsViewModel is responsible for managing the UI-related data for news articles.
 *
 * @param getNewsUseCase The use case used to fetch news data from the repository.
 */
class NewsViewModel(private val getNewsUseCase: GetNewsUseCase) : ViewModel() {

    // Private mutable state flow for holding the list of articles
    private val _news = MutableStateFlow<List<Article>>(emptyList())
    // Public state flow to expose the articles to the UI
    val news: StateFlow<List<Article>> get() = _news

    /**
     * Fetches news articles using the provided API key.
     *
     * This function launches a coroutine to fetch news data
     * and updates the _news state flow with the results.
     *
     * @param apiKey The API key required for authentication with the news API.
     */
    fun fetchNews(apiKey: String) {
        viewModelScope.launch {
            try {
                // Execute the use case to fetch news articles
                val result = getNewsUseCase.execute(apiKey)
                // Update the _news state flow with the fetched articles
                _news.value = result.articles
            } catch (e: Exception) {
                // Handle error and reset the _news state flow to an empty list
                _news.value = emptyList()
            }
        }
    }
}