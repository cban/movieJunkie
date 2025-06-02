package com.feature.movie.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.GetPopularMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getPopularMoviesUseCase: GetPopularMoviesUseCase,
) : ViewModel() {


    val moviesState: StateFlow<MovieUiState> = getPopularMoviesUseCase(1)
        .map { movies -> MovieUiState(movies = movies, isLoading = false, errorMessage = null) }
        .onStart { emit(MovieUiState(isLoading = true)) }
        .catch { exception ->
            emit(MovieUiState(errorMessage = exception.message ?: "Unknown error"))
        }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5000),
            initialValue = MovieUiState(isLoading = true)
        )

    fun handleEvent(event: UiEvents) {
        when (event) {
            is UiEvents.ShowDetail -> openDetail(event.id)

        }
    }

    fun openDetail(movieId: Int) {
        viewModelScope.launch {

        }
    }


}

