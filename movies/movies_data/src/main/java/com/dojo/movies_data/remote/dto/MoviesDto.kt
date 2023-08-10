package com.dojo.movies_data.remote.dto

import com.squareup.moshi.Json

data class MovieDto(
    val id: Int,
    val title: String,
    @field:Json(name = "release_date")
    val releaseDate: String,
    val overview: String,
    @field:Json(name = "poster_path")
    val posterPath: String,
    @field:Json(name = "backdrop_path")
    val backdropPath: String,
    @field:Json(name = "vote_average")
    val voteAverage: Float,
    @field:Json(name = "vote_count")
    val voteCount: Int,
    val adult: Boolean,
    @field:Json(name = "original_language")
    val originalLanguage: String,
    @field:Json(name = "original_title")
    val originalTitle: String,
    val popularity: Float,
    val video: Boolean,
    @field:Json(name = "genre_ids")
    val genreIds: List<Int>
)
