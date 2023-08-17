package com.bonobono.data.repository

import android.util.Log
import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.ChatService
import com.bonobono.data.remote.handleApi
import com.bonobono.data.websocket.ChatWebSocketListener
import com.bonobono.data.websocket.UnsafeOkHttpClient
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.chatting.ChatRoom
import com.bonobono.domain.model.chatting.Chatting
import com.bonobono.domain.model.chatting.ChattingList
import com.bonobono.domain.repository.chatting.ChattingRepository
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject

private const val TAG = "싸피"
class ChatRepositoryImpl @Inject constructor(
    private val chatService: ChatService,
) : ChattingRepository{
    private val chatWebSocketListener = ChatWebSocketListener()
    private var webSocket: WebSocket? = null
    override suspend fun getChattingList(): NetworkResult<List<ChattingList>> {
        return handleApi { chatService.getChattingList().map { it.toDomain() } }
    }

    override suspend fun postChatting(chatRoom: ChatRoom): NetworkResult<Chatting> {
        return handleApi { chatService.postChatting(chatRoom).toDomain() }
    }

    override suspend fun deleteChattingRoom(roomNumber: Int): NetworkResult<Unit> {
        return handleApi { chatService.deleteChattingRoom(roomNumber) }
    }

    override suspend fun connectWebSocket(url: String) {
        val unsafeOkHttpClient : UnsafeOkHttpClient
        Log.d(TAG, "connectWebSocket: url is ${url}")
        val request = Request.Builder()
            .url(url)
            .build()
        webSocket = OkHttpClient()
            .newWebSocket(request, chatWebSocketListener)
    }

    override suspend fun disconnectWebSocket() {
        webSocket?.close(NORMAL_CLOSURE_STATUS, null)
    }

    override suspend fun sendMessage(message: String) {
        webSocket?.send(message)
    }


    companion object {
        private const val NORMAL_CLOSURE_STATUS = 1000
    }
}