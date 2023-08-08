package com.dojo.login_domain.use_case

import com.dojo.login_domain.model.UserRegister
import com.dojo.login_domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class RegisterUser(
    private val repository: LoginRepository
) {
    operator fun invoke(user: UserRegister): Flow<Boolean> = repository.register(user)
}