package com.dojo.movies_data.repository

import com.dojo.movies_data.mapper.ToMovie
import com.dojo.movies_data.remote.MoviesApi
import com.dojo.movies_domain.model.Movie
import com.dojo.movies_domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow

class MoviesRepositoryImpl(
    private val moviesApi: MoviesApi
): MoviesRepository {

    override suspend fun getMovies(): Result<List<Movie>> {
        return try {
            Result.success(
                moviesApi.getMovies().results.map { it.ToMovie() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }

    override suspend fun searchMovies(query: String): Result<List<Movie>> {
        return try {
            Result.success(
                moviesApi.searchMovie(query = query).results.map { it.ToMovie() }
            )
        } catch (e: Exception) {
            e.printStackTrace()
            Result.failure(e)
        }
    }
}