package com.core.network.retrofit

import com.core.network.MoviesNetworkDataSource
import com.core.network.model.GenresDto
import com.core.network.model.MovieDetailDto
import com.core.network.model.MoviesDto
import javax.inject.Inject

internal class RetrofitNetworkDataSource @Inject constructor(private val apiService: MoviesApiService) :
    MoviesNetworkDataSource {
    override suspend fun getPopularMovies(page: Int): MoviesDto {
        return apiService.getPopularMovies(page)
    }

    override suspend fun getMovieGenres(): GenresDto {
        return apiService.getMovieGenres()
    }

    override suspend fun getMovieDetails(movieId: Int): MovieDetailDto {
        return apiService.getMovieDetails(movieId)
    }
}