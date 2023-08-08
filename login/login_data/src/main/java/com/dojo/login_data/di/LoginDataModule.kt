package com.dojo.login_data.di

import com.dojo.login_data.repository.LoginRepositoryImpl
import com.dojo.login_domain.repository.LoginRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LoginDataModule {

    @Binds
    abstract fun provideLoginRepository(impl: LoginRepositoryImpl): LoginRepository
}