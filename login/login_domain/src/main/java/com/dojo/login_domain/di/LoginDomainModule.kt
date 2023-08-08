package com.dojo.login_domain.di

import com.dojo.login_domain.repository.LoginRepository
import com.dojo.login_domain.use_case.IsLoggedIn
import com.dojo.login_domain.use_case.LoginUseCases
import com.dojo.login_domain.use_case.LoginUser
import com.dojo.login_domain.use_case.LogoutUser
import com.dojo.login_domain.use_case.RegisterUser
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
object LoginDomainModule {

    @ViewModelScoped
    @Provides
    fun provideLoginUseCases(
        repository: LoginRepository
    ): LoginUseCases {
        return LoginUseCases(
            loginUser = LoginUser(repository),
            registerUser = RegisterUser(repository),
            isLoggedIn = IsLoggedIn(repository),
            logoutUser = LogoutUser(repository)
        )
    }
}