package com.dojo.login_domain.use_case

data class LoginUseCases(
    val loginUser: LoginUser,
    val registerUser: RegisterUser,
    val isLoggedIn: IsLoggedIn,
    val logoutUser: LogoutUser
)