package com.feature.movie.ui

sealed class UiEvents {
    data class ShowDetail(val id: Int) : UiEvents()
}
