package com.bonobono.domain.model.character

data class UserCharacter(
    val char_ord_id: Int,
    val createdDate: String,
    val custom_name: String,
    val description: String,
    val experience: Int,
    val id: Int,
    val is_main: Boolean,
    val level: String,
    val memberId: Int
)