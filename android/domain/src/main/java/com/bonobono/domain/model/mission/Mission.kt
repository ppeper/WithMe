package com.bonobono.domain.model.mission

data class Mission (
    val problemId: Int = 0,
    val problem: String = "",
    val answer: String = "",
    val choices: List<Choice>? = listOf(),
    val commentary: String = ""
)