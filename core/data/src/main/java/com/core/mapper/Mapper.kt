package com.core.mapper

import com.core.domain.model.Movie
import com.core.network.BuildConfig
import com.core.network.model.MoviesDto


fun MoviesDto.toDomain(): List<Movie> {
    return results.map {
        Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            posterPath = buildImageUrl(it.posterPath),
            releaseDate = it.releaseDate,
            backdropPath = buildImageUrl(it.backdropPath),
        )
    }
}
fun com.core.network.model.Movie.toDomain(): Movie {
    return Movie(
        id = id,
        title = title,
        overview = overview,
        posterPath = buildImageUrl(posterPath),
        releaseDate = releaseDate,
        backdropPath = buildImageUrl(backdropPath)
    )
}



fun buildImageUrl(path: String?, size: String = "w500"): String? {
    return path?.let { "${BuildConfig.IMAGE_BASE_URL}$size$it" }
}
