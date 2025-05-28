package com.feature.movie.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.feature.movie.ui.MoviesRoute
import kotlinx.serialization.Serializable

@Serializable
object MoviesRoute

fun NavGraphBuilder.moviesScreen(
    onShowDetail: (Int) -> Unit,
    onBack: () -> Unit,
) {
    composable<MoviesRoute> {
        MoviesRoute(
            onShowDetail,
            onBack
        )
    }
}

