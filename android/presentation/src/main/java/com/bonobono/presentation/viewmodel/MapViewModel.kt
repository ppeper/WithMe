package com.bonobono.presentation.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Campaign
import com.bonobono.domain.model.map.CatchCharacter
import com.bonobono.domain.model.map.CatchKey
import com.bonobono.domain.model.map.Location
import com.bonobono.domain.model.map.Ranking
import com.bonobono.domain.usecase.map.GetCampaignUseCase
import com.bonobono.domain.usecase.map.GetCatchCharactersUseCase
import com.bonobono.domain.usecase.map.GetLocationsUseCase
import com.bonobono.domain.usecase.map.GetRankingUseCase
import com.bonobono.domain.usecase.map.PostCampaignUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapViewModel @Inject constructor(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val getCampaignUseCase: GetCampaignUseCase,
    private val getRankingUseCase: GetRankingUseCase,
    private val getCatchCharactersUseCase: GetCatchCharactersUseCase,
    private val postCampaignUseCase: PostCampaignUseCase
) : ViewModel() {

    private val _locations = MutableStateFlow<List<Location>>(listOf())
    val locations: StateFlow<List<Location>> = _locations

    private var _selectedLocation = MutableStateFlow<Location?>(null)
    val selectedLocation = _selectedLocation

    private var _writeCampaignState = MutableStateFlow<NetworkResult<Unit>?>(null)
    var writeCampaignState: StateFlow<NetworkResult<Unit>?> = _writeCampaignState

    fun setSelectedLocation(location: Location) {
        _selectedLocation.value = location
    }

    val location = mutableStateOf(Location())

    private val _ranking = MutableStateFlow<List<Ranking>>(listOf())
    val ranking: StateFlow<List<Ranking>> = _ranking

    private val _campaign = MutableStateFlow<List<Campaign>>(listOf())
    val campaign: StateFlow<List<Campaign>> = _campaign

    private val _catchCharacters = MutableStateFlow<List<CatchCharacter>>(listOf())
    val catchCharacters: StateFlow<List<CatchCharacter>> = _catchCharacters

    private val _curCatchCharacter = MutableStateFlow<CatchCharacter?>(null)
    val curCatchCharacter: StateFlow<CatchCharacter?> = _curCatchCharacter

    fun getLocations() = viewModelScope.launch {
        _locations.emit(getLocationsUseCase.invoke())
    }

    fun getRanking(locationId: Long) = viewModelScope.launch {
        _ranking.emit(getRankingUseCase.invoke(locationId))
    }

    fun getCampaign(locationId: Long) = viewModelScope.launch {
        _campaign.emit(getCampaignUseCase.invoke(locationId))
    }

    fun getCatchCharacters(catchKey: CatchKey) = viewModelScope.launch {
        _catchCharacters.emit(getCatchCharactersUseCase.invoke(catchKey))
    }

    fun postCampaign(campaign: Campaign) = viewModelScope.launch {
        _writeCampaignState.emit(postCampaignUseCase.invoke(campaign))
    }

    fun setLoading() {
        _writeCampaignState.value = NetworkResult.Loading
    }

    fun setCurCatchCharacter(catchCharacter: CatchCharacter) {
        _curCatchCharacter.value = catchCharacter
    }
}