package com.bonobono.domain.model.character

data class Character(
    val createdDate: String,
    val custom_name: String,
    val description: String,
    val experience: Int,
    val is_main: Boolean,
    val level: String,
    val memberId: Int
)