import SwiftUI

/**
 * The main entry point for the iOS application.
 *
 * This struct conforms to the App protocol and defines the main scene
 * of the application, which contains the initial user interface.
 */
@main
struct iOSApp: App {
    // The body of the app defines the main scene of the application.
    var body: some Scene {
        // Create a window group to manage the app's windows.
        WindowGroup {
            // Set the ContentView as the initial view of the application.
            ContentView()
        }
    }
}