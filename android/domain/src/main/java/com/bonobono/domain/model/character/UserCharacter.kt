package com.bonobono.domain.model.character

import java.util.Date

data class UserCharacter(
    val characterId: Int,
    val userId: Int,
    val customName: String,
    val experience: Int,
    val createdDate: Date,
    val isMain: Boolean
)
