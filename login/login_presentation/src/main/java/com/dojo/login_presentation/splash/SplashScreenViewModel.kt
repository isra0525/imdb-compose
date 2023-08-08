package com.dojo.login_presentation.splash

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.core.util.UiEvent
import com.dojo.login_domain.use_case.LoginUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashScreenViewModel @Inject constructor(
    private val loginUseCases: LoginUseCases
): ViewModel() {

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: SplashScreenEvent) {
        when(event) {
            is SplashScreenEvent.OnCheckIfUserIsLoggedIn -> {
                viewModelScope.launch(Dispatchers.IO) {
                    loginUseCases.isLoggedIn().also { isLoggedIn ->
                        isLoggedIn.collect {
                            if (it) {
                                onEvent(SplashScreenEvent.OnUserLogged)
                            } else {
                                onEvent(SplashScreenEvent.OnUserNotLogged)
                            }
                        }
/*                        if(isLoggedIn) {
                            onEvent(SplashScreenEvent.OnUserLogged)
                        } else {
                            onEvent(SplashScreenEvent.OnUserNotLogged)
                        }*/
                    }
                }
            }
            is SplashScreenEvent.OnUserNotLogged -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.Success)
                }
            }
            is SplashScreenEvent.OnUserLogged -> {
                viewModelScope.launch {
                    _uiEvent.send(UiEvent.NavigateToNextFeature)
                }
            }

        }
    }

}