package com.feature.moviedetail.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.feature.moviedetail.ui.MovieDetailRoute
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetailRoute(
    val movieId: Int,
)

fun NavGraphBuilder.movieDetailScreen(
    onBack: () -> Unit,
) {
    composable<MovieDetailRoute> { backStackEntry ->
        val route: MovieDetailRoute = backStackEntry.toRoute()
        MovieDetailRoute(
            movieId = route.movieId,
            onBack = onBack
        )
    }
}

fun NavController.navigateToMovieDetail(
    movieId: Int,
) {
    navigate(route = MovieDetailRoute(movieId = movieId))
}

