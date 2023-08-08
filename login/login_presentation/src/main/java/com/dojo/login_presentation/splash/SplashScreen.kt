package com.dojo.login_presentation.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.core.util.UiEvent
import com.dojo.core.R
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(
    onNavigateToLogin: () -> Unit,
    onNavigateToNextFeature: () -> Unit,
    onNavigateUp: () -> Unit,
    viewModel: SplashScreenViewModel = hiltViewModel()
) {

    LaunchedEffect(key1 = true) {
        delay(1500L)
        viewModel.onEvent(SplashScreenEvent.OnCheckIfUserIsLoggedIn)
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onNavigateToLogin()
                is UiEvent.NavigateToNextFeature -> onNavigateToNextFeature()
                UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.secondary),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(0.65f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.imdb),
                contentDescription = "IMDB Logo",
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}