package com.application.moviejunkie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.application.moviejunkie.navigation.MoviesNavGraph
import com.core.ui.theme.MovieJunkieTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            MovieJunkieTheme {
                MoviesNavGraph()
            }
        }
    }
}
