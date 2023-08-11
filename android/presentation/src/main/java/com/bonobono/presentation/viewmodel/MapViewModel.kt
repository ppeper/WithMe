package com.bonobono.presentation.viewmodel

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

    fun getLocations() = viewModelScope.launch {
        getLocationsUseCase.invoke()
    }
}