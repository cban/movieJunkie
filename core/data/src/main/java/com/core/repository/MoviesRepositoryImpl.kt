package com.core.repository

import com.core.datasource.MoviesDataSource
import com.core.domain.model.Movie
import com.core.domain.model.MovieDetail
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
            try {
                val genresResponse = moviesDataSource.getGenres()
                val moviesResponse = moviesDataSource.getMovies(page)

                val groupedMovies = MovieGroupingMapper.groupByGenre(moviesResponse, genresResponse)
                emit(groupedMovies)
            } catch (e: Exception) {
                //
            }
        }
    }

    override fun getMovieDetails(movieId: Int): Flow<MovieDetail> {
        return flow {
            try {
                val response = moviesDataSource.getMovieDetails(movieId)
                emit(response.toDomain())
            } catch (e: Exception) {
                throw e
            }
        }
    }
}