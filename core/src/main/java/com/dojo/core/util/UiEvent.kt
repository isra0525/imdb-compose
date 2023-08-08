package com.dojo.core.util

sealed class UiEvent {
    object Success: UiEvent()
    object NavigateUp: UiEvent()
    object NavigateToNextFeature: UiEvent()
    data class ShowSnackbar(val message: String): UiEvent()
}
