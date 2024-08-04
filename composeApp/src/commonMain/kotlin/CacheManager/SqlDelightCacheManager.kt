package CacheManager

import Model.Article
import Model.NewsResponse
import Model.Source
import com.newsapp.database.NewsDatabase

class SqlDelightCacheManager(private val database: NewsDatabase) : CacheManager {

    override fun cacheNews(newsResponse: NewsResponse) {

        database.newsDatabaseQueries.deleteAll()

        newsResponse.articles.forEach { article ->
            database.newsDatabaseQueries.insertArticle(
                title = article.title,
                author = article.author,
                url = article.url,
                description = article.description,
                urlToImage = article.urlToImage,
                publishedAt = article.publishedAt,
                content = article.content,
                sourceId = article.source.id,
                sourceName = article.source.name
            )
        }
    }

    override fun getCachedNews(): NewsResponse? {
        val articles = database.newsDatabaseQueries.selectAll().executeAsList().map {
            Article(
                source = Source(id = it.sourceId, name = it.sourceName),
                author = it.author,
                title = it.title,
                description = it.description,
                url = it.url,
                urlToImage = it.urlToImage,
                publishedAt = it.publishedAt,
                content = it.content
            )
        }

        return if (articles.isNotEmpty()) {
            NewsResponse(status = "success", totalResults = articles.size, articles = articles)
        } else {
            null
        }
    }
}