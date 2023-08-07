package com.bonobono.data.mapper

import com.bonobono.data.model.mission.response.ChoiceResponse
import com.bonobono.data.model.mission.response.MissionResponse
import com.bonobono.domain.model.mission.Choice
import com.bonobono.domain.model.mission.Mission


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