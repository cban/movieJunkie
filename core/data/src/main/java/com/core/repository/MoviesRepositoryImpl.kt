package com.core.repository

import com.core.datasource.MoviesDataSource
import com.core.domain.model.Movie
import com.core.domain.repository.MoviesRepository
import com.core.mapper.MovieGroupingMapper
import com.core.mapper.toDomain
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesDataSource: MoviesDataSource
) : MoviesRepository {
    override fun getPopularMovies(page: Int): Flow<List<Movie>> = flow {
        val moviesDto = moviesDataSource.getMovies(page)
        emit(moviesDto.toDomain())
    }


    override fun getPopularMoviesGroupedByGenre(
        page: Int
    ): Flow<Map<String, List<Movie>>> {

        return flow {
            val genresDto = moviesDataSource.getGenres()
            val moviesDto = moviesDataSource.getMovies(page)

            val groupedMovies = MovieGroupingMapper.groupByGenre(moviesDto, genresDto)
            emit(groupedMovies)
        }
    }
}