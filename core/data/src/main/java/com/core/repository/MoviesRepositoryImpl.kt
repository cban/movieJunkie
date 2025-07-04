package com.core.repository

import com.core.domain.model.Movie
import com.core.domain.model.MovieDetail
import com.core.domain.repository.MoviesRepository
import com.core.mapper.MovieGroupingMapper
import com.core.mapper.toDomain
import com.core.network.MoviesNetworkDataSource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MoviesRepositoryImpl @Inject constructor(
    private val moviesNetworkDataSource: MoviesNetworkDataSource
) : MoviesRepository {
    override fun getPopularMovies(page: Int): Flow<List<Movie>> = flow {
        val moviesDto = moviesNetworkDataSource.getPopularMovies(page)
        emit(moviesDto.toDomain())
    }


    override fun getPopularMoviesGroupedByGenre(
        page: Int
    ): Flow<Map<String, List<Movie>>> {

        return flow {
            try {
                val genresResponse = moviesNetworkDataSource.getMovieGenres()
                val moviesResponse = moviesNetworkDataSource.getPopularMovies(page)

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
                val response = moviesNetworkDataSource.getMovieDetails(movieId)
                emit(response.toDomain())
            } catch (e: Exception) {
                throw e
            }
        }
    }
}