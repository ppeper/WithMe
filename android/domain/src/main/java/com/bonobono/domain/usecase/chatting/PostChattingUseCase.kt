package com.bonobono.domain.usecase.chatting

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.chatting.ChatRoom
import com.bonobono.domain.model.chatting.Chatting
import com.bonobono.domain.repository.chatting.ChattingRepository
import javax.inject.Inject

class PostChattingUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository
) {
    suspend operator fun invoke(chatRoom: ChatRoom): NetworkResult<Chatting> {
        return chattingRepository.postChatting(chatRoom)
    }
}