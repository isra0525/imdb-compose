package com.dojo.login_domain.di

import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [LoginDomainModule::class]
)
object LoginDomainModuleTest {

}