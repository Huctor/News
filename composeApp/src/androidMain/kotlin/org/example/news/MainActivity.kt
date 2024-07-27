package org.example.news

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

/**
 * MainActivity class that serves as the entry point for the Android application.
 *
 * This class extends ComponentActivity and sets up the main content
 * view of the application using Jetpack Compose.
 */
class MainActivity : ComponentActivity() {

    // onCreate method, called when the activity is first created
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the content view to the Composable function NewsScreen
        setContent {
            NewsScreen()
        }
    }
}

/**
 * Preview function for the NewsScreen composable.
 *
 * This function provides a preview of the NewsScreen in the Android Studio
 * design editor, allowing for quick visual feedback during development.
 */
@Preview
@Composable
fun AppAndroidPreview() {
    // Calling the NewsScreen composable function
    NewsScreen()
}