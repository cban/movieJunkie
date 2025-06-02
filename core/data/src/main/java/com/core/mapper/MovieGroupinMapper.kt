package com.core.mapper

import com.core.domain.model.Movie
import com.core.network.model.GenresDto
import com.core.network.model.MoviesDto

object MovieGroupingMapper {
    fun groupByGenre(moviesDto: MoviesDto, genresDto: GenresDto): Map<String, List<Movie>> {
        val genreMap = genresDto.genres.associateBy { it.id }
        val grouped = moviesDto.results.groupBy { movie ->
            movie.genreIds.firstNotNullOfOrNull { genreId -> genreMap[genreId]?.name } ?: "Unknown"
        }
        return grouped.mapValues { (_, movies) ->
            movies.map { it.toDomain() }
        }
    }
}