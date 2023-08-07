package com.bonobono.data.mapper

import com.bonobono.data.model.mission.request.MissionRequest
import com.bonobono.data.model.mission.response.ChoiceResponse
import com.bonobono.data.model.mission.response.MissionResponse
import com.bonobono.data.model.mission.response.TotalScoreResponse
import com.bonobono.domain.model.mission.Choice
import com.bonobono.domain.model.mission.Mission
import com.bonobono.domain.model.mission.MissionResult
import com.bonobono.domain.model.mission.TotalScore


fun MissionResponse.toDomain() : Mission {
    return Mission(
        problem = problem,
        answer = answer,
        commentary = commentary,
        choices = choices.map { it.toDomain() },
    )
}

fun ChoiceResponse.toDomain() : Choice {
    return Choice(
        id = id,
        content = content
    )
}

fun MissionRequest.toDomain() : MissionResult {
    return MissionResult(
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