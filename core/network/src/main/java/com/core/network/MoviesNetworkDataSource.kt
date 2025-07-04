package com.core.network

import com.core.network.model.GenresDto
import com.core.network.model.MovieDetailDto
import com.core.network.model.MoviesDto

interface MoviesNetworkDataSource {
    suspend fun getPopularMovies(page: Int) : MoviesDto
    suspend fun getMovieGenres() : GenresDto
    suspend fun getMovieDetails(movieId: Int) : MovieDetailDto
}

