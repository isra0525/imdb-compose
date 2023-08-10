package com.dojo.movies_domain.repository

import com.dojo.movies_domain.model.Movie
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

        suspend fun getMovies(): Result<List<Movie>>

        suspend fun searchMovies(query: String): Result<List<Movie>>
}