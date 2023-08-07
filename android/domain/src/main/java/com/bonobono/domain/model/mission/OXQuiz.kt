package com.bonobono.domain.model.mission

import java.net.IDN

data class OXQuiz(
    val id: Int,
    val problem: String,
    val answer: String,
    val commentary: String
)