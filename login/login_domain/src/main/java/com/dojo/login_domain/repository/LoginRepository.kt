package com.dojo.login_domain.repository

import com.dojo.login_domain.model.User
import com.dojo.login_domain.model.UserRegister
import kotlinx.coroutines.flow.Flow

interface LoginRepository {

    fun login(user: User): Flow<Boolean>

    fun register(user: UserRegister): Flow<Boolean>

    suspend fun logout()

    val currentUser: Flow<Boolean>

}