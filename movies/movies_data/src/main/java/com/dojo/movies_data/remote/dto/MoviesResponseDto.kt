package com.dojo.movies_data.remote.dto

data class MoviesResponseDto(
    val page: Int,
    val results: List<MovieDto>,
    val total_pages: Int,
    val total_results: Int
)
