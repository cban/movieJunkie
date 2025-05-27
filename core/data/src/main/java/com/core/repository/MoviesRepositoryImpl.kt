package com.core.repository

import com.core.datasource.MoviesDataSource
import com.core.domain.model.Movie
import com.core.domain.repository.MoviesRepository
import com.core.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MoviesRepositoryImpl(
    private val moviesDataSource: MoviesDataSource
) : MoviesRepository {
    override fun getPopularMovies(page: Int): Flow<List<Movie>> = flow {
        val moviesDto = moviesDataSource.getMovies(page)
        emit(moviesDto.toDomain())
    }
}