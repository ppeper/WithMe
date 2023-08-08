package com.bonobono.data.model.mission.request

data class IsSuccessRequest(
    val answer: String,
    val memberId: Int,
    val problemId: Int
)