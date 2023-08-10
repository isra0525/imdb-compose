package com.dojo.movies_presentation.movies

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.hilt.navigation.compose.hiltViewModel
import com.dojo.core.util.UiEvent
import com.dojo.core_ui.components.SearchTextField
import com.dojo.core_ui.theme.LocalSpacing
import com.dojo.movies_presentation.movies.components.MovieItem

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun MoviesScreen(
    scaffoldState: ScaffoldState,
    onNavigateUp: () -> Unit,
    viewModel: MoviesScreenViewModel = hiltViewModel()
) {
    val state = viewModel.state.collectAsState()
    val keyBoardController = LocalSoftwareKeyboardController.current
    val spacing = LocalSpacing.current
    LaunchedEffect(key1 = true) {
        viewModel.onEvent(MoviesEvent.OnLoadMovies)
    }
    LaunchedEffect(key1 = keyBoardController) {
        viewModel.uiEvent.collect { event ->
            when(event) {
                is UiEvent.ShowSnackbar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message
                    )
                    keyBoardController?.hide()
                }
                is UiEvent.NavigateUp -> onNavigateUp()
                else -> Unit
            }
        }
    }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceMedium)
    ) {
        SearchTextField(
            text = state.value.query,
            onValueChange = {
                viewModel.onEvent(MoviesEvent.OnQueryChange(it))
            },
            shouldShowHint = state.value.isHintVisible,
            onSearch = {
                keyBoardController?.hide()
                viewModel.onEvent(MoviesEvent.OnSearch)
            },
            onFocusChanged = {
                viewModel.onEvent(MoviesEvent.OnSearchFocusChange(it.isFocused))
            }
        )
        Spacer(modifier = Modifier.height(spacing.spaceMedium))
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(state.value.movies) { movie ->
                MovieItem(movie = movie)
                Spacer(modifier = Modifier.height(spacing.spaceMedium))
            }
        }

    }
}