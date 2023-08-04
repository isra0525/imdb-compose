package com.dojo.login_domain.use_case

import com.dojo.login_domain.model.User
import com.dojo.login_domain.repository.LoginRepository

class LoginUser(
    private val loginRepository: LoginRepository
) {
    suspend operator fun invoke(user: User) = loginRepository.login(user)
}