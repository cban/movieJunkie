package com.core.domain

sealed class MovieException : Exception() {
    data class ServerError(val code: Int) : MovieException()
    data class GenericError(val errorMessage: String?) : MovieException()
}