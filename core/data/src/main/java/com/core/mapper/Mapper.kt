package com.core.mapper

import com.core.domain.model.Movie
import com.core.network.model.MoviesDto


fun MoviesDto.toDomain(): List<Movie> {
    return results.map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            posterPath = it.posterPath,
            releaseDate = it.releaseDate,
            backdropPath = it.backdropPath
        )
    }
}