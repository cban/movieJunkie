package com.feature.moviedetail.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.domain.GetMovieDetailsUseCase
import com.core.domain.MovieException
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(private val getMovieDetailsUseCase: GetMovieDetailsUseCase) :
    ViewModel() {

    private val _uiState = MutableStateFlow(
        MovieDetailUiState(
            false, null, null
        )
    )
    val uiState = _uiState
    fun getMovieDetails(movieId: Int) {
        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }

            try {
                val movieDetail = getMovieDetailsUseCase(movieId).first()
                _uiState.update {
                    it.copy(isLoading = false, movieDetail = movieDetail, errorMessage = null)
                }
            } catch (e: MovieException) {
                val errorMessage = when (e) {
                    is MovieException.ServerError -> "Server error occurred (${e.code})"
                    is MovieException.GenericError -> "${e.message}"
                }
                _uiState.update { it.copy(isLoading = false, errorMessage = errorMessage) }
            }
        }
    }

}