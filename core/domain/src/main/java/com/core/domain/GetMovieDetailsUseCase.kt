package com.core.domain

import com.core.domain.model.MovieDetail
import com.core.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(private val repository: MoviesRepository) {

    operator fun invoke(movieId: Int): Flow<MovieDetail> {
        return repository.getMovieDetails(movieId)
            .catch { error ->
                throw MovieException.GenericError(
                    error.message ?: "An unknown error occurred",
                )
            }
    }

}