package com.dojo.login_presentation.splash

sealed class SplashScreenEvent {
    object OnCheckIfUserIsLoggedIn: SplashScreenEvent()
    object OnUserLogged: SplashScreenEvent()
    object OnUserNotLogged: SplashScreenEvent()
}
