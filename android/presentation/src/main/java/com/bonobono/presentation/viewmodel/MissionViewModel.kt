package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.usecase.mission.GetFourQuizUseCase
import com.bonobono.domain.usecase.mission.GetMiniGameUseCase
import com.bonobono.domain.usecase.mission.GetOXQuizUseCase
import com.bonobono.domain.usecase.mission.PostAttendanceUseCase
import com.bonobono.domain.usecase.mission.PostIsSuccessFourQuizUseCase
import com.bonobono.domain.usecase.mission.PostIsSuccessOXQuizUseCase
import com.bonobono.domain.usecase.mission.time.GetCompletedTimeUseCase
import com.bonobono.domain.usecase.mission.time.PutCompletedTimeUseCase
import com.bonobono.presentation.utils.Constants
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.util.Calendar
import javax.inject.Inject

@HiltViewModel
class MissionViewModel @Inject constructor(
    private val getOXQuizUseCase: GetOXQuizUseCase,
    private val getFourQuizUseCase: GetFourQuizUseCase,
    private val getMiniGameUseCase: GetMiniGameUseCase,
    private val postAttendanceUseCase: PostAttendanceUseCase,
    private val postIsSuccessOXQuizUseCase: PostIsSuccessOXQuizUseCase,
    private val postIsSuccessFourQuizUseCase: PostIsSuccessFourQuizUseCase,
    private val putCompletedTimeUseCase: PutCompletedTimeUseCase,
    private val getCompletedTimeUseCase: GetCompletedTimeUseCase
) : ViewModel() {

    private val _mission = MutableStateFlow<NetworkResult<Mission>>(NetworkResult.Loading)
    val mission: StateFlow<NetworkResult<Mission>> = _mission

    private val _isSuccess = MutableStateFlow<NetworkResult<Boolean>>(NetworkResult.Loading)
    val isSuccess: StateFlow<NetworkResult<Boolean>> = _isSuccess

    fun getMission(memberId: Int, type: String) = viewModelScope.launch {
        when (type) {
            Constants.OX_QUIZ -> _mission.emit(getOXQuizUseCase.invoke(memberId))
            Constants.FOUR_QUIZ -> _mission.emit(getFourQuizUseCase.invoke(memberId))
            Constants.GAME -> _mission.emit(getMiniGameUseCase.invoke(memberId))
        }
    }

    fun postIsSuccess(isSuccess: IsSuccess, type: String) = viewModelScope.launch {
        when (type) {
            Constants.OX_QUIZ -> _isSuccess.emit(postIsSuccessOXQuizUseCase.invoke(isSuccess))
            Constants.FOUR_QUIZ -> _isSuccess.emit(postIsSuccessFourQuizUseCase.invoke(isSuccess))
        }
    }

    fun postAttendance(memberId: Int) = viewModelScope.launch {
        postAttendanceUseCase.invoke(memberId)
    }

    fun putCompletedTime(key: String, time: Long) = viewModelScope.launch {
        putCompletedTimeUseCase.invoke(key, time)
    }

    fun getCompletedTime(key: String): Long {
        return getCompletedTimeUseCase(key)
    }

//    fun getCompletedTime(key: String) = viewModelScope.launch {
//        val timeStamp = getCompletedTimeUseCase.invoke(key)
//        val calendarToday =
//            Calendar.getInstance().apply { timeInMillis = System.currentTimeMillis() }
//        val calendarGet = Calendar.getInstance().apply { timeInMillis = timeStamp }
//
//        _isSuccess.value =
//            calendarToday.get(Calendar.YEAR) == calendarGet.get(Calendar.YEAR) &&
//                    calendarToday.get(Calendar.MONTH) == calendarGet.get(Calendar.MONTH) &&
//                    calendarToday.get(Calendar.DAY_OF_MONTH) == calendarGet.get(Calendar.DAY_OF_MONTH)
//    }

}