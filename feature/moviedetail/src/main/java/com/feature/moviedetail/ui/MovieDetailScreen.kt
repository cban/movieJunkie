package com.feature.moviedetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.core.ui.MovieAppBar

@Composable
internal fun MovieDetailRoute(
    movieId: Int,
    onBack: () -> Unit,
) {
    MovieDetailScreen(
        movieId = movieId,
        onBack,
    )
}


@Composable
fun MovieDetailScreen(
    movieId: Int,
    onBack: () -> Unit
) {
    MovieDetailContent(
        movieId = movieId,
        onBack = onBack
    )
}

@Composable
fun MovieDetailContent(
    movieId: Int,
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            MovieAppBar(
                title = "Movie Detail",
                onBack = onBack,
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier.padding(paddingValues).fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text(text = "Movie Detail Screen for Movie ID: $movieId")
        }
    }
}