package com.dojo.login_presentation.register

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.core.util.UiEvent
import com.dojo.login_domain.model.User
import com.dojo.login_domain.model.UserRegister
import com.dojo.login_domain.use_case.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterScreenViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    var user by mutableStateOf(UserRegister("", "", ""))
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: RegisterEvent) {
        when(event) {
            is RegisterEvent.OnRegister -> {
                executeRegister(event.user)
            }
            is RegisterEvent.OnEmailEnter -> {
                user = user.copy(email = event.value)
            }
            is RegisterEvent.OnPasswordEnter -> {
                user = user.copy(password = event.value)
            }
            is RegisterEvent.OnNameEnter -> {
                user = user.copy(name = event.value)
            }
            is RegisterEvent.OnRegisterSuccess -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success)
                }
            }
        }
    }

    private fun executeRegister(user: UserRegister) {
        viewModelScope.launch {
            loginUseCases.registerUser(user).collect { register ->
                if (register) {
                    _uiEvent.send(UiEvent.Success)
                } else {
                    _uiEvent.send(UiEvent.ShowSnackbar("Register failed"))
                }
            }
        }
    }

}