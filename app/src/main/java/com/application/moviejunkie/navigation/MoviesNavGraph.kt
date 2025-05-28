package com.application.moviejunkie.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.feature.movie.navigation.MoviesRoute
import com.feature.movie.navigation.moviesScreen
import com.feature.moviedetail.navigation.movieDetailScreen
import com.feature.moviedetail.navigation.navigateToMovieDetail

@Composable
fun MoviesNavGraph() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MoviesRoute,
    ) {
        moviesScreen(
            onShowDetail = { movieId ->
                navController.navigateToMovieDetail(movieId)
            },
            onBack = {
                navController.popBackStack()
            }
        )
        movieDetailScreen(
            onBack = {
                navController.popBackStack()
            }
        )
    }
}