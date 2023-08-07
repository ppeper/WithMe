package com.bonobono.domain.model.character

import java.util.Date


data class OurCharacter(
    val characterId: Int,
    val name: String,
    // 이미지?
    val imageName: String,
    val imageUrl: String,
    val description: String,
    val level: Int
)