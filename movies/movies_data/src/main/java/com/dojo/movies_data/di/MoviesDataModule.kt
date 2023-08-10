package com.dojo.movies_data.di

import com.dojo.movies_data.remote.MoviesApi
import com.dojo.movies_data.repository.MoviesRepositoryImpl
import com.dojo.movies_domain.repository.MoviesRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MoviesDataModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BASIC
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideMoviesApi(client: OkHttpClient): MoviesApi {
        return Retrofit.Builder()
            .baseUrl(MoviesApi.BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .client(client)
            .build()
            .create(MoviesApi::class.java)
    }

    @Provides
    fun provideMoviesRepository(
        api: MoviesApi
    ): MoviesRepository {
        return MoviesRepositoryImpl(
            moviesApi = api
        )
    }
}