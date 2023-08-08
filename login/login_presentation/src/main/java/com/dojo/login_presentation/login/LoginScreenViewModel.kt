package com.dojo.login_presentation.login

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.core.util.UiEvent
import com.dojo.login_domain.model.User
import com.dojo.login_domain.use_case.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class LoginScreenViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
) : ViewModel() {

    var user by mutableStateOf(User("", ""))
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: LoginEvent) {
        when(event) {
            is LoginEvent.OnLogin -> {
                executeLogin(event.user)
            }
            is LoginEvent.OnEmailEnter -> {
                user = user.copy(email = event.value)
            }
            is LoginEvent.OnPasswordEnter -> {
                user = user.copy(password = event.value)
            }
        }
    }

    private fun executeLogin(user: User) {
        viewModelScope.launch {
            loginUseCases.loginUser(user).collect { login ->
                if (login) {
                    _uiEvent.send(UiEvent.Success)
                } else {
                    _uiEvent.send(UiEvent.ShowSnackbar("Login failed"))
                }
            }
        }
    }

}