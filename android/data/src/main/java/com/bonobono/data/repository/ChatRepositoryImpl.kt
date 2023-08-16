package com.bonobono.data.repository

import com.bonobono.data.mapper.toDomain
import com.bonobono.data.remote.ChatService
import com.bonobono.data.remote.handleApi
import com.bonobono.data.websocket.WebSocketListener
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.chatting.ChatRoom
import com.bonobono.domain.model.chatting.Chatting
import com.bonobono.domain.model.chatting.ChattingList
import com.bonobono.domain.repository.chatting.ChattingRepository
import javax.inject.Inject

class ChatRepositoryImpl @Inject constructor(
    private val chatService: ChatService,
    private val webSocketListener: WebSocketListener,
) : ChattingRepository {
    override suspend fun getChattingList(): NetworkResult<List<ChattingList>> {
        return handleApi { chatService.getChattingList().map { it.toDomain() } }
    }

    override suspend fun postChatting(chatRoom: ChatRoom): NetworkResult<Chatting> {
        return handleApi { chatService.postChatting(chatRoom).toDomain() }
    }

    override suspend fun deleteChattingRoom(roomNumber: Int): NetworkResult<Unit> {
        return handleApi { chatService.deleteChattingRoom(roomNumber) }
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