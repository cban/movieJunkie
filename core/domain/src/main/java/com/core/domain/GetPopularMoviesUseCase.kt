package com.core.domain

import com.core.domain.model.Movie
import com.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class GetPopularMoviesUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {
    operator fun invoke(): Flow<List<Movie>> =
        (moviesRepository.getPopularMovies(1))

    operator fun invoke(page: Int): Flow<Map<String, List<Movie>>> =
        (moviesRepository.getPopularMoviesGroupedByGenre(page))
}


