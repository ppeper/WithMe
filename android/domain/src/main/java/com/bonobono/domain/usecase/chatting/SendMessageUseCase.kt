package com.bonobono.domain.usecase.chatting

import com.bonobono.domain.repository.chatting.ChattingRepository
import javax.inject.Inject

class SendMessageUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository
) {
    suspend operator fun invoke(message: String) {
        return chattingRepository.sendMessage(message)
    }
}