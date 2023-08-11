package com.bonobono.data.model.character.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserCharacterResponse(
    val char_ord_id: Int,
    val createdDate: String,
    val custom_name: String,
    val description: String,
    val experience: Int,
    val id: Int,
    val is_main: Boolean,
    val level: String,
    val memberId: Int
) : Parcelable