import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.ui.tooling.preview.Preview
import news.composeapp.generated.resources.Res
import news.composeapp.generated.resources.compose_multiplatform

/**
 * App is a composable function that displays a button and an animated greeting message.
 *
 * When the button is clicked, it toggles the visibility of a greeting message
 * and an image. This function uses Jetpack Compose for the UI layout.
 */
@Composable
@Preview
fun App() {
    MaterialTheme {
        // State to control the visibility of the greeting content
        var showContent by remember { mutableStateOf(false) }

        // Main layout of the App
        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            // Button to toggle the visibility of the greeting content
            Button(onClick = { showContent = !showContent }) {
                Text("Click me!")
            }
            // Animated visibility of the greeting content
            AnimatedVisibility(showContent) {
                // Remember the greeting message to avoid re-calculation on recomposition
                val greeting = remember { Greeting().greet() }
                // Layout for the greeting content
                Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                    // Display an image
                    Image(painterResource(Res.drawable.compose_multiplatform), null)
                    // Display the greeting message
                    Text("Compose: $greeting")
                }
            }
        }
    }
}