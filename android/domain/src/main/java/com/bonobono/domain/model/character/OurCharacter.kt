package com.bonobono.domain.model.character

import java.util.Date


data class OurCharacter(
    val characterId: Int,
    val characterOrdId: Int,
    val name: String,
    val description: String,
    val level: Int
)