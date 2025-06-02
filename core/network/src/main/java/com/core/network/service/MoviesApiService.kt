package com.core.network.service

import com.core.network.model.GenresDto
import com.core.network.model.MoviesDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int,
    ): MoviesDto

    @GET("genre/movie/list")
    suspend fun getMovieGenres(): GenresDto

}