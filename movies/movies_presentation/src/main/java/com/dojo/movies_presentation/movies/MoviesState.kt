package com.dojo.movies_presentation.movies

import com.dojo.movies_domain.model.Movie


data class MoviesState(
    val movies: List<Movie> = emptyList(),
    val isSearching: Boolean = false,
    val isHintVisible: Boolean = false,
    val query: String = ""
)