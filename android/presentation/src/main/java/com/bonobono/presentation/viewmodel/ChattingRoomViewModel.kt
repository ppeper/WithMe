package com.bonobono.presentation.viewmodel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.chatting.ChatRoom
import com.bonobono.domain.model.chatting.Chatting
import com.bonobono.domain.usecase.chatting.ConnectWebSocketUseCase
import com.bonobono.domain.usecase.chatting.DisconnectWebSocketUseCase
import com.bonobono.domain.usecase.chatting.PostChattingUseCase
import com.bonobono.domain.usecase.chatting.SendMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import okhttp3.WebSocketListener
import javax.inject.Inject

private const val TAG = "싸피"
@HiltViewModel
class ChattingRoomViewModel @Inject constructor(
    private val postChattingUseCase: PostChattingUseCase,
    private val connectWebSocketUseCase: ConnectWebSocketUseCase,
    private val disconnectWebSocketUseCase: DisconnectWebSocketUseCase,
    private val sendMessageUseCase: SendMessageUseCase
): ViewModel() {
    private val _messageList = MutableStateFlow<NetworkResult<Chatting>>(NetworkResult.Loading)
    val messageList = _messageList.asStateFlow()

    var roomNumber by mutableStateOf(0)
        private set

    fun enterChattingRoom(nickName : String) = viewModelScope.launch {
        val chatRoom = ChatRoom(nickname = nickName)
        Log.d(TAG, "enterChattingRoom: nickname is ${nickName}")
        _messageList.emit(postChattingUseCase.invoke(chatRoom))
        Log.d(TAG, "enterChattingRoom: ${messageList.value}")
        val result = messageList.value
        when(result) {
            is NetworkResult.Success -> {
                roomNumber = result.data.roomNumber
            }
            else -> {
            }
        }
        Log.d(TAG, "enterChattingRoom: ${roomNumber}")
        connectWebSocket()
    }

    fun connectWebSocket() = viewModelScope.launch {
        connectWebSocketUseCase.invoke("https://i9d105.p.ssafy.io:8081/ws/chat/${roomNumber}")
    }

    fun disconnectSocket() = viewModelScope.launch {
        disconnectWebSocketUseCase.invoke()
    }

    fun sendMessage() = viewModelScope.launch {
        sendMessageUseCase.invoke("test")
    }
}