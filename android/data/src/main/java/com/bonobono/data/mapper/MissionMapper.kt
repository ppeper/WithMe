package com.bonobono.data.mapper

import com.bonobono.data.model.mission.request.IsSuccessRequest
import com.bonobono.data.model.mission.response.ChoiceResponse
import com.bonobono.data.model.mission.response.MiniGameResponse
import com.bonobono.data.model.mission.response.MissionResponse
import com.bonobono.data.model.mission.response.TotalScoreResponse
import com.bonobono.domain.model.mission.Choice
import com.bonobono.domain.model.mission.IsSuccess
import com.bonobono.domain.model.mission.MiniGame
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.model.mission.TotalScore


fun MissionResponse.toDomain() : Mission {
    return Mission(
        problemId = problemId,
        problem = problem,
        answer = answer,
        commentary = commentary,
        choices = choices?.map { it.toDomain() },
    )
}

fun ChoiceResponse.toDomain() : Choice {
    return Choice(
        id = id,
        content = content
    )
}

fun IsSuccessRequest.toDomain() : IsSuccess {
    return IsSuccess(
        problemId = problemId,
        memberId = memberId,
        answer = answer
    )
}

fun TotalScoreResponse.toDomain() : TotalScore {
    return TotalScore(
        attendanceScore = attendanceScore,
        totalScore = totalScore
    )
}

fun MiniGameResponse.toDomain() : MiniGame {
    return MiniGame(
        answer = answer,
        problem = problem,
        problemId = problemId
    )
}