package com.dojo.imdb

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Scaffold
import androidx.compose.material.rememberScaffoldState
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dojo.imdb.navigation.Route
import com.dojo.core_ui.theme.ImdbTheme
import com.dojo.login_presentation.login.LoginScreen
import com.dojo.login_presentation.register.RegisterScreen
import com.dojo.login_presentation.splash.SplashScreen
import com.dojo.movies_presentation.movies.MoviesScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ImdbTheme {
                val navController = rememberNavController()
                val scaffoldState = rememberScaffoldState()

                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    scaffoldState = scaffoldState
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = Route.SPLASH
                    ) {

                        composable(Route.SPLASH) {
                            SplashScreen(
                                onNavigateToLogin = { navController.navigate(Route.LOGIN) },
                                onNavigateToNextFeature = { navController.navigate(Route.MOVIES) },
                                onNavigateUp = { navController.navigateUp() }
                            )
                        }
                        composable(Route.LOGIN) {
                            LoginScreen(
                                scaffoldState = scaffoldState,
                                onNavigateToNextFeature = { navController.navigate(Route.MOVIES) },
                                onNavigateToRegister = { navController.navigate(Route.REGISTER) },

                            )
                        }
                        composable(Route.REGISTER) {
                            RegisterScreen(
                                scaffoldState = scaffoldState,
                                onNavigateUp = { navController.navigateUp() },
                                onSuccessfulRegister = { navController.navigate(Route.MOVIES) },
                            )
                        }
                        composable(Route.MOVIES) {
                            MoviesScreen()
                        }
                    }
                }
            }
        }
    }
}
