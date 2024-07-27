package org.example.news

import Model.Article
import Model.Source
import ViewModel.NewsViewModel
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.koin.androidx.compose.koinViewModel

/**
 * Main composable function for displaying the news screen.
 *
 * This function collects the state of news articles from the ViewModel
 * and fetches the news when it is first launched. It displays a list
 * of news articles using a LazyColumn for efficient scrolling.
 */
@Composable
fun NewsScreen(viewModel: NewsViewModel = koinViewModel()) {
    // Collecting the state of news articles from the ViewModel
    val newsState by viewModel.news.collectAsState()

    // Fetching news when the composable is launched
    LaunchedEffect(Unit) {
        viewModel.fetchNews(apiKey = "")
    }

    // Displaying the list of news articles
    LazyColumn {
        items(newsState) { article ->
            NewsItem(article)
        }
    }
}

/**
 * Composable function to display a single news item.
 *
 * This function creates a vertical layout for the news article,
 * displaying the title, author, publication date, and description.
 * An optional image can be loaded if a URL is provided.
 */
@Composable
fun NewsItem(article: Article) {
    Column(modifier = Modifier.padding(8.dp)) {
        // Display the title of the article with bold text and larger font size
        Text(
            text = article.title,
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 22.sp
            )
        )

        // Display the author if available with normal weight
        article.author?.let {
            Text(
                text = "by $it",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }

        // Display the publication date with smaller font size
        Text(
            text = article.publishedAt,
            style = TextStyle(
                fontSize = 14.sp,
                fontWeight = FontWeight.Light
            )
        )

        // Placeholder for image loading
        article.urlToImage?.let {
            // Placeholder image loading implementation
            // If you don't want to use coil, comment out this part
        }

        // Display the description if available with normal weight
        article.description?.let {
            Text(
                text = it,
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal
                )
            )
        }
    }
}

// Preview function to display a sample news item in the Compose preview
@Preview(showBackground = true)
@Composable
fun NewsItemPreview() {
    NewsItem(
        Article(
            source = Source(id = "1", name = "Example Source"),
            author = "Author Name",
            title = "Sample Title",
            description = "Sample Description",
            url = "https://example.com",
            urlToImage = "https://example.com/image.jpg",
            publishedAt = "2024-01-01T00:00:00Z",
            content = "Sample Content"
        )
    )
}