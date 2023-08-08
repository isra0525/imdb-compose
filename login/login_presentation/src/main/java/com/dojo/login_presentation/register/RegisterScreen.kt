package com.dojo.login_presentation.register

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.core.R
import com.dojo.core.util.UiEvent
import com.dojo.core_ui.components.ImdbButton
import com.dojo.core_ui.components.ImdbInput
import com.dojo.core_ui.styles.largeTextStyle
import com.dojo.core_ui.theme.LocalSpacing

@Composable
fun RegisterScreen(
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit,
    onSuccessfulRegister: () -> Unit,
    viewModel: RegisterScreenViewModel = hiltViewModel()
) {
    val user = viewModel.user
    val spacing = LocalSpacing.current

    LaunchedEffect(key1 = true) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.Success -> onSuccessfulRegister()
                UiEvent.NavigateToNextFeature -> TODO()
                UiEvent.NavigateUp -> TODO()
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                }
            }
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row {
            IconButton(
                onClick = { onNavigateUp() },
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back to Login"
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center) {
            Column(
                modifier = Modifier
                    .fillMaxWidth(0.8f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.imdb),
                    contentDescription = "IMDB Logo",
                    modifier = Modifier
                        .fillMaxWidth(0.3f)
                        .height(60.dp)
                        .background(
                            color = MaterialTheme.colors.secondary,
                            shape = MaterialTheme.shapes.medium
                        )
                        .padding(10.dp)
                )
                Spacer(modifier = Modifier.height(spacing.spaceLarge))
                Text(text = "Crear una cuenta", style = largeTextStyle)
                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                ImdbInput(
                    onValueChange = { viewModel.onEvent(RegisterEvent.OnNameEnter(it)) },
                    inputName = "Nombre",
                    text = user.name
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                ImdbInput(
                    onValueChange = { viewModel.onEvent(RegisterEvent.OnEmailEnter(it)) },
                    inputName = "Correo electrónico",
                    text = user.email
                )
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
                ImdbInput(
                    onValueChange = { viewModel.onEvent(RegisterEvent.OnPasswordEnter(it)) },
                    inputName = "Contraseña",
                    text = user.password,
                    visualTransformation = PasswordVisualTransformation()
                )
                Spacer(modifier = Modifier.height(spacing.spaceLarge))

                // agregar validaciones
                ImdbButton(
                    text = "Aceptar",
                    onClick = { viewModel.onEvent(RegisterEvent.OnRegister(user)) },
                    modifier = Modifier.fillMaxWidth(),
                    enabled = viewModel.user.email.isNotEmpty() && viewModel.user.password.isNotEmpty() && viewModel.user.name.isNotEmpty()
                )
            }
        }
    }
}