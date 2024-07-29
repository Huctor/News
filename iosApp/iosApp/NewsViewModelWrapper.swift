import Foundation
import ComposeApp

/**
 * NewsViewModelWrapper is an ObservableObject that acts as a bridge
 * between the SwiftUI views and the NewsViewModel.
 *
 * This wrapper manages the state of news articles and allows
 * SwiftUI views to reactively update when the news data changes.
 */
class NewsViewModelWrapper: ObservableObject {
    private let viewModel: NewsViewModel // The underlying ViewModel for fetching news
    private let cacheManager: IosCacheManager

    @Published var news: [Article] = [] // Published property for news articles

    /**
     * Initializes the NewsViewModelWrapper by creating necessary instances
     * of the use case and the view model, and starts collecting news data.
     */
    init() {
        cacheManager = IosCacheManager()
        // Create instances of necessary classes for fetching news
        let getNewsUseCase = GetNewsUseCase(newsRepository: NewsRepository(apiService: NewsApiService(client: Modules_ios_ktKt.createHttpClient()),cacheManager: cacheManager))
        viewModel = NewsViewModel(getNewsUseCase: getNewsUseCase)
        collectNews()
    }

    /**
     * Collects news data from the ViewModel and updates the published news property.
     *
     * This method observes the state flow of news articles and updates the
     * published property whenever new articles are available.
     */
    private func collectNews() {
        let flowWrapper = FlowWrapperFactory.companion.create(stateFlow: viewModel.news)
        flowWrapper.observe { [weak self] articles in
            DispatchQueue.main.async {
                self?.news = articles as? [Article] ?? []
            }
        }
    }

    /**
     * Fetches news articles using the provided API key.
     *
     * This method calls the fetchNews method on the underlying ViewModel
     * to retrieve news articles from the API.
     *
     * @param apiKey The API key required for fetching news data.
     */
    func fetchNews(apiKey: String) {
        viewModel.fetchNews(apiKey: apiKey)
    }
}
