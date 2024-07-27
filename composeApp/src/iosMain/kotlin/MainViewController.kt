import androidx.compose.ui.window.ComposeUIViewController
import DI.initKoin

/**
 * MainViewController is a function that creates a Compose UIViewController.
 *
 * This function initializes Koin for dependency injection and sets up the
 * Compose UI by calling the App composable function.
 *
 * @return A UIViewController configured with Jetpack Compose.
 */
fun MainViewController() = ComposeUIViewController(
    // Configuration block to initialize Koin for dependency injection
    configure = {
        initKoin()
    }
) {
    // Set the content of the UIViewController to the App composable
    App()
}