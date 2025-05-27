package com.core.datasource.remote

import com.core.datasource.MoviesDataSource
import com.core.network.model.MoviesDto
import com.core.network.service.MoviesApiService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MoviesRemoteDataSource @Inject constructor(private val apiService: MoviesApiService) :
    MoviesDataSource {
    override suspend fun getMovies(page: Int):MoviesDto {
        return apiService.getPopularMovies(page)
    }
}