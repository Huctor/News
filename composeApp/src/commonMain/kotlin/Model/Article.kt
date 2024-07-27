package Model

import kotlinx.serialization.Serializable

// Data class representing an Article, annotated with @Serializable to make it serializable
@Serializable
data class Article(
    val source: Source,
    val author: String?,
    val title: String,
    val description: String?,
    val url: String,
    val urlToImage: String?,
    val publishedAt: String,
    val content: String?
)

// Data class representing the source of an Article, annotated with @Serializable to make it serializable
@Serializable
data class Source(
    val id: String?,
    val name: String
)
