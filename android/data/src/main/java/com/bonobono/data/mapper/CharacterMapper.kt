package com.bonobono.data.mapper

import com.bonobono.data.model.character.response.UserCharacterResponse
import com.bonobono.domain.model.character.UserCharacter

fun UserCharacterResponse.toDomain(): UserCharacter {
    return UserCharacter(
        char_ord_id, createdDate, custom_name, description, experience, id, is_main, level, memberId
    )
}