package com.dojo.login_domain.use_case

import com.dojo.login_domain.repository.LoginRepository

class IsLoggedIn(
    private val repository: LoginRepository
) {
    suspend operator fun invoke() = repository.isLoggedIn()
}