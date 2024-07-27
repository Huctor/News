package Model

import kotlinx.serialization.Serializable

// Data class representing a response from a news API, annotated with @Serializable to make it serializable
@Serializable
data class NewsResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<Article>
)