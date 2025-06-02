package com.feature.moviedetail.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.core.ui.MovieAppBar

@Composable
internal fun MovieDetailRoute(
    movieId: Int,
    onBack: () -> Unit,
    viewModel: MovieDetailViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MovieDetailScreen(
        movieId = movieId,
        uiState = uiState,
        viewModel = viewModel,
        onBack,
    )
}


@Composable
fun MovieDetailScreen(
    movieId: Int,
    uiState: MovieDetailUiState,
    viewModel: MovieDetailViewModel,
    onBack: () -> Unit
) {

    LaunchedEffect(movieId) {
        viewModel.getMovieDetails(movieId)
    }

    MovieDetailContent(
        uiState = uiState,  //
        movieId = movieId,
        onBack = onBack
    )
}

@Composable
fun MovieDetailContent(
    uiState: MovieDetailUiState,
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
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when {
                uiState.isLoading -> {
                    CircularProgressIndicator()
                }

                uiState.errorMessage != null -> {
                    Text(text = "Error: ${uiState.errorMessage}")
                }

                uiState.movieDetail != null -> {

                    Text(text = "Movie: ${uiState.movieDetail.title}")

                }

                else -> {
                    Text(text = "Movie Detail Screen for Movie ID: $movieId")
                }
            }
        }
    }
}