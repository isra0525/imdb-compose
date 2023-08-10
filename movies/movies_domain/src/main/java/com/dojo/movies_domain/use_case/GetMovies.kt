package com.dojo.movies_domain.use_case

import com.dojo.movies_domain.repository.MoviesRepository

class GetMovies(
    val repository: MoviesRepository
) {
    suspend operator fun invoke() = repository.getMovies()
}