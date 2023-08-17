package com.bonobono.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.chatting.ChattingList
import com.bonobono.domain.usecase.chatting.DeleteChattingRoomUseCase
import com.bonobono.domain.usecase.chatting.GetChattingListUseCase
import com.bonobono.domain.usecase.chatting.PostChattingUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val TAG = "싸피"
@HiltViewModel
class ChattingViewModel@Inject constructor(
    private val getChattingListUseCase: GetChattingListUseCase,
    private val deleteChattingRoomUseCase: DeleteChattingRoomUseCase
): ViewModel() {
    private val _chattingList = MutableStateFlow<NetworkResult<List<ChattingList>>>(NetworkResult.Loading)
    val chattingList = _chattingList.asStateFlow()

    fun getChattingList() = viewModelScope.launch {
        _chattingList.emit(getChattingListUseCase.invoke())
        Log.d(TAG, "getChattingList: ${chattingList.value}")
    }

    fun deleteChattingRoom(roomNumber : Int) = viewModelScope.launch {
        deleteChattingRoomUseCase.invoke(roomNumber)
    }

    // 아이템 삭제 메소드
    fun deleteChattingItem(item: ChattingList) {
        val currentList = _chattingList.value
        if (currentList is NetworkResult.Success) {
            val updatedList = currentList.data.toMutableList()
            updatedList.remove(item)
            _chattingList.value = NetworkResult.Success(updatedList)
        }
    }

}