package com.core.domain.repository

import com.core.domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

     fun getPopularMovies(page: Int): Flow<List<Movie>>
}