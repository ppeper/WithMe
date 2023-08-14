package com.bonobono.domain.model.character

data class UserCharacter(
    val char_ord_id: Int = 0,
    val createdDate: String = "",
    val custom_name: String = "",
    val description: String = "",
    val experience: Int = 0,
    val id: Int = 0,
    val is_main: Boolean = false,
    val level: String = "",
    val memberId: Int = 0
)