package com.bonobono.domain.model.character

data class SaveCharacter(
    val custom_name: String,
    val location_name: String,
    val memberId: Int,
    val ourCharacterId: Int
)