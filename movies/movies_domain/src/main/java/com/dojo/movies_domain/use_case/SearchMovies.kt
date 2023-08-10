package com.dojo.movies_domain.use_case

import com.dojo.movies_domain.repository.MoviesRepository

class SearchMovies(
    val repository: MoviesRepository
) {
    suspend operator fun invoke(query: String) = repository.searchMovies(query)
}
