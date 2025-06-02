package com.feature.movie.ui

import com.core.domain.model.Movie

data class MovieUiState(
    val isLoading: Boolean = false,
    val movies: Map<String , List<Movie>> = emptyMap(),
    val errorMessage: String? = null
)

sealed class MovieUiStates() {
    object Loading : MovieUiStates()
    data class Success(val movies: List<Movie>) : MovieUiStates()
    data class Error(val message: String) : MovieUiStates()
}