package com.bonobono.domain.usecase.chatting

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.repository.chatting.ChattingRepository
import javax.inject.Inject

class ConnectWebSocketUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository
) {
    suspend operator fun invoke(url: String) {
        chattingRepository.connectWebSocket(url)
    }
}