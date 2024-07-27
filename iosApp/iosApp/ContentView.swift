import UIKit
import SwiftUI
import ComposeApp

/**
 * ComposeView is a struct that conforms to UIViewControllerRepresentable,
 * allowing integration of a UIKit UIViewController within SwiftUI.
 */
struct ComposeView: UIViewControllerRepresentable {
    // Creates the UIViewController to be displayed
    func makeUIViewController(context: Context) -> UIViewController {
        MainViewControllerKt.MainViewController()
    }

    // Updates the UIViewController when the SwiftUI view state changes
    func updateUIViewController(_ uiViewController: UIViewController, context: Context) {}
}

/**
 * ContentView is a SwiftUI view that displays a list of news articles.
 *
 * It observes the view model for changes in the news data and fetches news articles
 * when the view appears.
 */
struct ContentView: View {
    @ObservedObject private var viewModel = NewsViewModelWrapper() // Create an instance of the view model

        var body: some View {

            List(viewModel.news, id: \.url) { article in
            // Display each news article using the NewsItem view
                NewsItem(article: article)
            }
            .onAppear {
            // Fetch news articles when the view appears
                viewModel.fetchNews(apiKey: "")
            }
        }
}

/**
 * ContentView_Previews provides a preview of the ContentView for SwiftUI previews.
 */
struct ContentView_Previews: PreviewProvider {
    static var previews: some View {
        ContentView() // Return an instance of ContentView for preview
    }
}



