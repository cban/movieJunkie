package com.core.datasource

import com.core.network.model.GenresDto
import com.core.network.model.MovieDetailDto
import com.core.network.model.MoviesDto
import kotlinx.coroutines.flow.Flow

interface MoviesDataSource {
    suspend fun getMovies(page :Int) : MoviesDto

    suspend fun getGenres(): GenresDto

    suspend fun getMovieDetails(movieId: Int): MovieDetailDto

}