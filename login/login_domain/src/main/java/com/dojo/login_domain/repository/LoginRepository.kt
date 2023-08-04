package com.dojo.login_domain.repository

import com.dojo.login_domain.model.User

interface LoginRepository {

    suspend fun login(user: User): Result<Boolean>

    suspend fun register(user: User): Result<Boolean>

    suspend fun logout(): Result<Boolean>

    suspend fun isLoggedIn(): Result<Boolean>

}