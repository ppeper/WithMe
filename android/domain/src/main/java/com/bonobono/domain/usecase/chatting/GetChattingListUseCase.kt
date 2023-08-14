package com.bonobono.domain.usecase.chatting

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.chatting.ChattingList
import com.bonobono.domain.repository.chatting.ChattingRepository
import javax.inject.Inject

class GetChattingListUseCase @Inject constructor(
    private val chattingRepository: ChattingRepository
) {
    suspend operator fun invoke(): NetworkResult<List<ChattingList>> {
        return chattingRepository.getChattingList()
    }
}