package com.bonobono.data.mapper

import com.bonobono.data.model.chatting.request.ChattingRoomRequest
import com.bonobono.data.model.chatting.response.ChatMessageResponse
import com.bonobono.data.model.chatting.response.ChattingListResponse
import com.bonobono.data.model.chatting.response.ChattingResponse
import com.bonobono.domain.model.chatting.ChatRoom
import com.bonobono.domain.model.chatting.Chatting
import com.bonobono.domain.model.chatting.ChattingList
import com.bonobono.domain.model.chatting.ChattingMessage

fun ChattingRoomRequest.toDomain(): ChatRoom {
    return ChatRoom(
        nickname = nickname,
    )
}

fun ChatMessageResponse.toDomain(): ChattingMessage {
    return ChattingMessage(
        userName = userName,
        msg = msg,
        imageUrl = imageUrl,
        createdDate = createdDate
    )
}

fun ChattingResponse.toDomain(): Chatting {
    return Chatting(
        nickname = nickname,
        roomNumber = roomNumber,
        profileImgName = profileImgName,
        profileImgUrl = profileImgUrl,
        messages = messages.map { it.toDomain() }
    )
}

fun ChattingListResponse.toDomain(): ChattingList {
    return ChattingList(
        nickname = nickname,
        roomNumber = roomNumber,
        profileImgName = profileImgName,
        profileImgUrl = profileImgUrl,
        msg = msg,
        messageCreatedDate = messageCreatedDate
    )
}