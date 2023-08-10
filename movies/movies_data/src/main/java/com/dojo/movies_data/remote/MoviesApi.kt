package com.dojo.movies_data.remote

import com.dojo.movies_data.remote.dto.MoviesResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface MoviesApi {

    @GET("movie/top_rated")
    suspend fun getMovies(
        @Query("api_key") apiKey: String = API_KEY,
    ): MoviesResponseDto

    @GET("search/movie")
    suspend fun searchMovie(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") query: String
    ): MoviesResponseDto

    companion object {
        const val BASE_URL = "https://api.themoviedb.org/3/"
        const val API_KEY = "2ff12aa9688677737e75f96c91c76ced"
    }
}