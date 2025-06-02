package com.feature.moviedetail.ui

import com.core.domain.model.MovieDetail

data class MovieDetailUiState(
    val isLoading: Boolean,
    val movieDetail: MovieDetail?,
    val errorMessage: String?
)