package com.bonobono.data.model.map.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class OurCharacterResponse(
    val charOrdId: Int,
    val description: String,
    val id: Int,
    val level: String,
    val name: String
) : Parcelable