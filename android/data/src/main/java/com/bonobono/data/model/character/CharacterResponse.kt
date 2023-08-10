package com.bonobono.data.model.character

data class CharacterResponse(
    val createdDate: String,
    val custom_name: String,
    val description: String,
    val experience: Int,
    val is_main: Boolean,
    val level: String,
    val memberId: Int
)