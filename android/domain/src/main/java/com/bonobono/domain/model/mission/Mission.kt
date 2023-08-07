package com.bonobono.domain.model.mission

data class Mission (
    val problem: String,
    val answer: String,
    val choices: List<Choice>,
    val commentary: String
)