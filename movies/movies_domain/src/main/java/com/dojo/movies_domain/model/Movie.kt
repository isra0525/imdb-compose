package com.dojo.movies_domain.model

data class Movie(
    val id: Int,
    val title: String,
    val releaseDate: String?,
    val overview: String,
    val posterPath: String?,
    val backdropPath: String?,
    val voteAverage: Float?,
    val voteCount: Int?,
    val adult: Boolean,
    val originalLanguage: String?,
    val originalTitle: String?,
    val popularity: Float?,
    val video: Boolean,
    val genreIds: List<Int>?
)
