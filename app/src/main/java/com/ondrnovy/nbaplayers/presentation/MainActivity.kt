package com.ondrnovy.nbaplayers.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.ondrnovy.nbaplayers.presentation.routing.Router
import com.ondrnovy.nbaplayers.presentation.theme.NBAPlayersTheme

/**
 * Main activity of the app.
 */
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NBAPlayersTheme {
                Router()
            }
        }
    }
}