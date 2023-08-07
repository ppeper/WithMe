package com.bonobono.domain.model.mission

data class Quiz (
    val id: Int,
    val problem: String,
    val answer: String,
    val choices: List<Choice>,
    val commentary: String
)