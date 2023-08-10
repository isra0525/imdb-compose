package com.dojo.movies_presentation.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dojo.core.util.UiEvent
import com.dojo.movies_domain.use_case.MoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesScreenViewModel @Inject constructor(
    private val useCases: MoviesUseCases
): ViewModel() {
    var state = MutableStateFlow(MoviesState())
        private set

    private val _uiEvent = Channel<UiEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    fun onEvent(event: MoviesEvent) {
        when(event) {
            is MoviesEvent.OnLoadMovies -> {
                execGetTopRatedMovies()
            }
            is MoviesEvent.OnQueryChange -> {
                state.value = state.value.copy(query = event.query)
            }
            is MoviesEvent.OnSearch -> executeSearch()
            is MoviesEvent.OnSearchFocusChange -> {
                state.value = state.value.copy(
                    isHintVisible = !event.isFocussed && state.value.query.isBlank(),
                    movies = emptyList()
                )
            }
        }
    }

    fun execGetTopRatedMovies(){
        viewModelScope.launch(Dispatchers.IO) {
            useCases.getMovies()
                .onSuccess { movieList ->
                    state.value = state.value.copy(
                        movies = movieList
                    )
                }
                .onFailure {
                    state.value = state.value.copy(isSearching = false)
                    _uiEvent.send(UiEvent.ShowSnackbar("Error loading movies"))
            }
        }
    }

    fun executeSearch() {
        viewModelScope.launch(Dispatchers.IO) {
            useCases.searchMovies(state.value.query)
                .onSuccess {
                    movies ->
                    state.value = state.value.copy(
                        movies = movies,
                        isSearching = false
                    )
                }
                .onFailure {
                    state.value = state.value.copy(isSearching = false)
                    _uiEvent.send(UiEvent.ShowSnackbar("Error searching movies"))
                }
        }
    }
}