package com.feature.movie.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.core.domain.model.Movie
import com.core.ui.MovieAppBar
import com.core.ui.theme.MovieJunkieTheme

@Composable
internal fun MoviesRoute(
    onShowDetail: (Int) -> Unit,
    onBack: () -> Unit,
    viewModel: MoviesViewModel = hiltViewModel()
) {
    val movieState by viewModel.moviesState.collectAsStateWithLifecycle()

    MoviesScreen(
        uiState = movieState,
        onShowDetail,
        onBack,
        onEvent = viewModel::handleEvent
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(
    uiState: MovieUiState,
    onShowDetail: (Int) -> Unit,
    onBack: () -> Unit,
    onEvent: (UiEvents) -> Unit
) {

    MoviesContent(
        state = uiState,
        onShowDetail = onShowDetail,
        onBack = onBack,
        onEvent = onEvent
    )
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesContent(
    state: MovieUiState,
    onShowDetail: (Int) -> Unit,
    onBack: () -> Unit,
    onEvent: (UiEvents) -> Unit
) {
    Scaffold(
        topBar = {
            MovieAppBar(
                title = "Movies",
                onBack = onBack,
            )
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {
            when {
                state.isLoading -> {
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        contentAlignment = Alignment.Center,


                        ) {
                        CircularProgressIndicator(
                            modifier = Modifier
                                .size(48.dp),
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                }

                state.errorMessage != null -> {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                            .padding(16.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = state.errorMessage,
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.colorScheme.error
                        )
                    }

                }

                else -> {
                    MoviesList(
                        movies = state.movies,
                        onShowDetail = onShowDetail,
                        onEvent = onEvent
                    )
                }
            }
        }
    }
}

@Composable
fun MoviesList(
    movies: Map<String, List<Movie>>,
    onShowDetail: (Int) -> Unit,
    onEvent: (UiEvents) -> Unit
) {
    if (movies.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "No movies available",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
        return
    }
    LazyColumn {
        movies.forEach { (genre, movieList) ->
            item {
                Text(
                    text = genre,
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
                    ),
                    modifier = Modifier.padding(16.dp)
                )
            }
            item {
                LazyRow(
                    contentPadding = PaddingValues(horizontal = 12.dp)
                ) {
                    items(movieList) { movie ->
                        PaddingValues(horizontal = 4.dp)
                        MovieItem(
                            movie = movie,
                            onClick = {
                                onShowDetail(movie.id)
                                onEvent(UiEvents.ShowDetail(movie.id))
                            },
                        )
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}


@Composable
fun MovieItem(
    movie: Movie,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {

    Card(
        modifier = modifier
            .width(140.dp)
            .height(140.dp)
            .padding(8.dp)
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(12.dp),
                spotColor = Color.Gray.copy(alpha = 0.5f),
                ambientColor = Color.Gray.copy(alpha = 0.5f)
            )
            .clickable(onClick = onClick),
        shape = RoundedCornerShape(12.dp)
    ) {
        Box {
            AsyncImage(
                model = movie.posterPath,
                modifier = Modifier
                    .fillMaxSize()
                    .height(100.dp),
                contentScale = ContentScale.Crop,
                contentDescription = "movie poster",
                error = rememberVectorPainter(Icons.Default.Clear),
            )
            Box(
                modifier = Modifier
                    .align(Alignment.BottomEnd)
                    .fillMaxWidth()
                    .background(color = Color.Black.copy(alpha = 0.4f))
            ) {
                Text(
                    text = movie.title,
                    modifier = Modifier
                        .padding(8.dp),
                    color = Color.White,
                    style = TextStyle(
                        fontSize = 8.sp,
                    ),
                    maxLines = 1,
                    overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    MovieJunkieTheme {
        val movies = listOf(
            Movie(
                id = 1,
                title = "Spider-Man Into the Spider-verse ",
                overview = "Description 1",
                releaseDate = "2023-01-01",
                posterPath = "https://example.com/poster1.jpg",
                backdropPath = "https://example.com/backdrop1.jpg"
            ),
            Movie(
                id = 2,
                title = "Movie 2",
                overview = "Description 1",
                releaseDate = "2023-01-01",
                posterPath = "https://example.com/poster1.jpg",
                backdropPath = "https://example.com/backdrop1.jpg"
            ),
            Movie(
                id = 3,
                title = "Movie 3",
                overview = "Description 1",
                releaseDate = "2023-01-01",
                posterPath = "https://example.com/poster1.jpg",
                backdropPath = "https://example.com/backdrop1.jpg"
            ),
        )

        val moviesByGenre = mapOf(
            "Action" to movies,
            "Adventure" to movies,
            "Comedy" to movies,
        )

        MoviesContent(
            state = MovieUiState(movies = moviesByGenre),
            onShowDetail = {},
            onBack = {},
            onEvent = {}
        )
    }
}

