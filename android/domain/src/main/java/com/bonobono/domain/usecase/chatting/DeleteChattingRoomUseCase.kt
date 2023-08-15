package com.bonobono.domain.usecase.chatting

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.repository.chatting.ChattingRepository
import javax.inject.Inject

class DeleteChattingRoomUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository
) {
    suspend operator fun invoke(roomNumber : Int): NetworkResult<Unit> {
        return chattingRepository.deleteChattingRoom(roomNumber)
    }
}