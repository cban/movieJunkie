package com.core.datasource

import com.core.network.model.MoviesDto
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {
    suspend fun getMovies(page :Int) : MoviesDto
}