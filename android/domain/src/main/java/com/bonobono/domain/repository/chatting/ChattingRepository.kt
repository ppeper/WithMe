package com.bonobono.domain.repository.chatting

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.chatting.ChatRoom
import com.bonobono.domain.model.chatting.Chatting
import com.bonobono.domain.model.chatting.ChattingList

interface ChattingRepository {
    // 채팅방 목록 호출
    suspend fun getChattingList() : NetworkResult<List<ChattingList>>

    // 채팅 값 호출
    suspend fun postChatting(chatRoom: ChatRoom) : NetworkResult<Chatting>

    // 채팅방 삭제
    suspend fun deleteChattingRoom(roomNumber : Int) : NetworkResult<Unit>

//    // websocket 연결
    suspend fun connectWebSocket(url: String)
//    // websocket 끊기
    suspend fun disconnectWebSocket()

    // websocket 소통
    suspend fun sendMessage(message : String)
}