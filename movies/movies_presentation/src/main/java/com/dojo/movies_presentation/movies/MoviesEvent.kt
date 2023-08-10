package com.dojo.movies_presentation.movies


sealed class MoviesEvent {
    object OnLoadMovies: MoviesEvent()
    data class OnQueryChange(val query: String): MoviesEvent()
    object OnSearch: MoviesEvent()
    data class OnSearchFocusChange(val isFocussed: Boolean): MoviesEvent()
}