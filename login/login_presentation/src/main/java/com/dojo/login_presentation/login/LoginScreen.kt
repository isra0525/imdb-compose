package com.dojo.login_presentation.login

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.core.R
import com.dojo.core.util.UiEvent
import com.dojo.core_ui.components.ImdbButton
import com.dojo.core_ui.components.ImdbInput
import com.dojo.core_ui.styles.mediumTextStyle
import com.dojo.core_ui.styles.smallTextStyle
import com.dojo.core_ui.theme.LocalSpacing
import com.dojo.login_presentation.login.components.LoginTypeImage

@Composable
fun LoginScreen(
    scaffoldState: ScaffoldState,
    onNavigateToNextFeature: () -> Unit,
    onNavigateToRegister: () -> Unit,
    viewModel: LoginScreenViewModel = hiltViewModel()
) {
    val user = viewModel.user
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onNavigateToNextFeature()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
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
            modifier = Modifier.fillMaxWidth(0.8f).height(550.dp),
        ){
            Column(
                modifier = Modifier.fillMaxSize(),
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround,
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.imdb),
                        contentDescription = "IMDB Logo",
                        modifier = Modifier
                            .fillMaxWidth(0.4f)
                            .height(100.dp)
                    )
                }
                ImdbInput(
                    onValueChange = { viewModel.onEvent(LoginEvent.OnEmailEnter(it)) },
                    inputName = "Email",
                    text = user.email
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                ImdbInput(
                    onValueChange = { viewModel.onEvent(LoginEvent.OnPasswordEnter(it)) },
                    inputName = "Contraseña",
                    visualTransformation = PasswordVisualTransformation(),
                    text = user.password
                )
                Spacer(modifier = Modifier.height(spacing.spaceExtraSmall))
                Text(
                    text ="¿Olvidaste la contraseña?",
                    style = smallTextStyle,
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                ImdbButton(
                    text = "Login",
                    onClick = { viewModel.onEvent(LoginEvent.OnLogin(user))},
                    modifier = Modifier.fillMaxWidth(),
                    enabled = viewModel.user.email.isNotEmpty() && viewModel.user.password.isNotEmpty()
                )
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text ="Ó podes ingresar con",
                        style = mediumTextStyle
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                ) {
                    LoginTypeImage(
                        image = R.drawable.apple,
                        modifier = Modifier.size(70.dp)
                    )
                    LoginTypeImage(
                        image = R.drawable.facebook,
                        modifier = Modifier.size(70.dp)
                    )
                    LoginTypeImage(
                        image = R.drawable.google,
                        modifier = Modifier.size(70.dp)
                    )
                }
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center
                ) {
                    Text(
                        text ="¿No tienes cuenta?",
                        style = mediumTextStyle,
                    )
                    Text(
                        text =" Regístrate",
                        style = mediumTextStyle.copy(
                            fontWeight = FontWeight.Bold
                        ),
                        modifier = Modifier.clickable { onNavigateToRegister() },
                    )
                }
            }
        }
    }
}