package com.dojo.login_presentation.login

import com.dojo.login_domain.model.User

data class LoginState(
    val user: User = User("", ""),
    val isLoginFormValid: Boolean = false
)