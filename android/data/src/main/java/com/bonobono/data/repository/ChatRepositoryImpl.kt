package com.bonobono.data.repository

import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.ChatService
import com.bonobono.data.remote.handleApi
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.chatting.ChatRoom
import com.bonobono.domain.model.chatting.Chatting
import com.bonobono.domain.model.chatting.ChattingList
import com.bonobono.domain.repository.chatting.ChattingRepository
import okhttp3.Response
import okhttp3.WebSocket
import okhttp3.WebSocketListener
import okio.ByteString
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatService: ChatService
) : ChattingRepository, WebSocketListener() {
    override suspend fun getChattingList(): NetworkResult<List<ChattingList>> {
        return handleApi { chatService.getChattingList().map { it.toDomain() } }
    }

    override suspend fun postChatting(chatRoom: ChatRoom): NetworkResult<Chatting> {
        return handleApi { chatService.postChatting(chatRoom).toDomain() }
    }

    override suspend fun deleteChattingRoom(roomNumber: Int): NetworkResult<Unit> {
        return handleApi { chatService.deleteChattingRoom(roomNumber) }
    }

    override fun onClosed(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosed(webSocket, code, reason)

    }

    override fun onClosing(webSocket: WebSocket, code: Int, reason: String) {
        super.onClosing(webSocket, code, reason)

    }

    override fun onFailure(webSocket: WebSocket, t: Throwable, response: Response?) {
        super.onFailure(webSocket, t, response)

    }

    override fun onMessage(webSocket: WebSocket, text: String) {
        super.onMessage(webSocket, text)

    }

    override fun onMessage(webSocket: WebSocket, bytes: ByteString) {
        super.onMessage(webSocket, bytes)

    }

    override fun onOpen(webSocket: WebSocket, response: Response) {
        super.onOpen(webSocket, response)

    }

    override suspend fun connectWebSocket(url: String): NetworkResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun disconnectWebSocket(): NetworkResult<Unit> {
        TODO("Not yet implemented")
    }

    override suspend fun sendMessage(message: String): NetworkResult<Unit> {
        TODO("Not yet implemented")
    }
}