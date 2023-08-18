package com.bonobono.presentation.viewmodel

import android.location.Location
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

data class MainScreenUiState(
    val locationFromGps: Location? = null
)

class CommunityMapViewModel : ViewModel() {

    var uiState by mutableStateOf(MainScreenUiState())
        private set

    fun setLocationFromGps(location: Location?) {
        if (uiState.locationFromGps == null && uiState.locationFromGps != location) {
            uiState = uiState.copy(locationFromGps = location)
        }
    }

}