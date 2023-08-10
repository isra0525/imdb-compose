package com.dojo.movies_domain.di

import com.dojo.movies_domain.repository.MoviesRepository
import com.dojo.movies_domain.use_case.GetMovies
import com.dojo.movies_domain.use_case.MoviesUseCases
import com.dojo.movies_domain.use_case.SearchMovies
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object MoviesDomainModule {

    @ViewModelScoped
    @Provides
    fun provideMoviesUseCases(
        repository: MoviesRepository
    ): MoviesUseCases {
        return MoviesUseCases(
            searchMovies = SearchMovies(repository),
            getMovies = GetMovies(repository)
        )
    }

}