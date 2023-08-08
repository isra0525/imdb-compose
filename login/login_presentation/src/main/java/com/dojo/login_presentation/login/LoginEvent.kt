package com.dojo.login_presentation.login

import com.dojo.login_domain.model.User

sealed class LoginEvent {
    data class OnLogin(val user: User) : LoginEvent()
    data class OnEmailEnter(val value: String) : LoginEvent()
    data class OnPasswordEnter(val value: String) : LoginEvent()
}