package com.core.domain.repository

import com.core.domain.model.Movie
import com.core.domain.model.MovieDetail
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    fun getPopularMovies(page: Int): Flow<List<Movie>>
    fun getPopularMoviesGroupedByGenre(page: Int): Flow<Map<String, List<Movie>>>
    fun getMovieDetails(movieId: Int): Flow<MovieDetail>

}