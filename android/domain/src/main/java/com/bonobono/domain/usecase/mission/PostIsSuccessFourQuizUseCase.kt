package com.bonobono.domain.usecase.mission

import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.repository.MissionRepository
import javax.inject.Inject

class PostIsSuccessFourQuizUseCase @Inject constructor(
    private val missionRepository: MissionRepository,
) {
    suspend operator fun invoke(isSuccess: IsSuccess) : NetworkResult<Boolean> {
        return missionRepository.postIsSuccessFourQuiz(isSuccess.memberId, isSuccess.problemId, isSuccess.answer)
    }
}