package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.usecase.mission.GetFourQuizUseCase
import com.bonobono.domain.usecase.mission.GetMiniGameUseCase
import com.bonobono.domain.usecase.mission.GetOXQuizUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MissionViewModel @Inject constructor(
    private val getOXQuizUseCase: GetOXQuizUseCase,
    private val getFourQuizUseCase: GetFourQuizUseCase,
    private val getMiniGameUseCase: GetMiniGameUseCase
) : ViewModel() {
    private val _oxQuizState = MutableStateFlow<NetworkResult<Mission>>(NetworkResult.Loading)
    val oxQuizState = _oxQuizState.asStateFlow()

    private val _fourQuizState = MutableStateFlow<NetworkResult<Mission>>(NetworkResult.Loading)
    val fourQuizState = _fourQuizState.asStateFlow()

    private val _miniGameState = MutableStateFlow<NetworkResult<Mission>>(NetworkResult.Loading)
    val miniGameState = _miniGameState.asStateFlow()

    fun getOXQuiz(memberId: Int) = viewModelScope.launch {
        _oxQuizState.value = NetworkResult.Loading
        _oxQuizState.emit(getOXQuizUseCase.invoke(memberId = memberId))
    }

    fun getFourQuiz(memberId: Int) = viewModelScope.launch {
        _fourQuizState.value = NetworkResult.Loading
        _fourQuizState.emit(getFourQuizUseCase.invoke(memberId = memberId))
    }

    fun getMiniGame(memberId: Int) = viewModelScope.launch {
        _miniGameState.value = NetworkResult.Loading
        _miniGameState.emit(getMiniGameUseCase.invoke(memberId = memberId))
    }
}