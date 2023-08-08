package com.dojo.login_domain.use_case

import com.dojo.login_domain.model.User
import com.dojo.login_domain.repository.LoginRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class LoginUser(
    private val loginRepository: LoginRepository
) {
    operator fun invoke(user: User): Flow<Boolean> = loginRepository.login(user)
}