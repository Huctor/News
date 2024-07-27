import UIKit
import SwiftUI
import ComposeApp

/**
 * NewsItem is a SwiftUI view that represents a single news article.
 *
 * It displays the title, author, publication date, an optional image,
 * and an optional description of the article.
 */
struct NewsItem: View {
    var article: Article // The news article to display

    var body: some View {
        VStack(alignment: .leading, spacing: 8) {
            // Display the article title
            Text(article.title)
                .font(.headline)
            // Optionally display the author if available
            if let author = article.author {
                Text("by \(author)")
                    .font(.subheadline)
                    .foregroundColor(.secondary)
            }
            // Display the publication date
            Text(article.publishedAt)
                .font(.caption)
                .foregroundColor(.secondary)
            // Optionally display the article image if available
            if let urlToImage = article.urlToImage, let imageUrl = URL(string: urlToImage) {
                AsyncImage(url: imageUrl) { image in
                    image.resizable()
                        .aspectRatio(contentMode: .fill)
                } placeholder: {
                    // Placeholder for loading image
                    Color.gray
                }
                .frame(height: 180)
                .clipped()
            }

            // Optionally display the article description if available
            if let description = article.description_ {
                Text(description)
                    .font(.body)
            }
        }
        .padding(.vertical, 8) // Add vertical padding to the NewsItem view
    }
}
