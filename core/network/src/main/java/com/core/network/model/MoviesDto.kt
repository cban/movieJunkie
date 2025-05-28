package com.core.network.model

import com.squareup.moshi.Json


data class MoviesDto(
    val page: Int,
    val results: List<Movie>,
    @Json(name = "total_pages") val totalPages: Int,
    @Json(name = "total_results") val totalResults: Int
)
