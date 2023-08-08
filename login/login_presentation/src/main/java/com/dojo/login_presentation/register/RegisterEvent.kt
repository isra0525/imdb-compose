package com.dojo.login_presentation.register

import com.dojo.login_domain.model.UserRegister

sealed class RegisterEvent {
    data class OnRegister(val user: UserRegister) : RegisterEvent()
    data class OnNameEnter(val value: String) : RegisterEvent()
    data class OnEmailEnter(val value: String) : RegisterEvent()
    data class OnPasswordEnter(val value: String) : RegisterEvent()
    object OnRegisterSuccess: RegisterEvent()
}