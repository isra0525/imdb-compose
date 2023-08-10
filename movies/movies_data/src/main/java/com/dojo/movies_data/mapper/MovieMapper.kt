package com.dojo.movies_data.mapper

import com.dojo.movies_data.remote.dto.MovieDto

fun MovieDto.ToMovie() = com.dojo.movies_domain.model.Movie(
    id = id,
    title = title,
    overview = overview,
    posterPath = posterPath,
    backdropPath = backdropPath,
    voteAverage = voteAverage,
    voteCount = voteCount,
    adult = adult,
    originalLanguage = originalLanguage,
    originalTitle = originalTitle,
    popularity = popularity,
    releaseDate = releaseDate,
    video = video,
    genreIds = genreIds
)