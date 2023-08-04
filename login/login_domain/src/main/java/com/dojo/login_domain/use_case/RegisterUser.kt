package com.dojo.login_domain.use_case

import com.dojo.login_domain.model.User
import com.dojo.login_domain.repository.LoginRepository

class RegisterUser(
    private val repository: LoginRepository
) {
    suspend operator fun invoke(user: User) = repository.register(user)
}