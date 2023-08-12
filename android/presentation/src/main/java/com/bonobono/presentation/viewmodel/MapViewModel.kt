package com.bonobono.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.map.Location
import com.bonobono.domain.usecase.map.GetLocationsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase
) : ViewModel() {

    private val _locations = MutableStateFlow<List<Location>>(listOf())
    val locations: StateFlow<List<Location>> = _locations

    private var _selectedLocation = MutableStateFlow<Location?>(null)
    val selectedLocation = _selectedLocation

    fun setSelectedLocation(location: Location) {
        _selectedLocation.value = location
    }

    fun getLocations() = viewModelScope.launch {
        _locations.emit(getLocationsUseCase.invoke())
    }
}