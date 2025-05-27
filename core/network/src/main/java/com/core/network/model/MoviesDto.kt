package com.core.network.model


data class MoviesDto(
    val page: Int,
    val results: List<Movie>,
    val totalPages: Int,
    val totalResults: Int
)
