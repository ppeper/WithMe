package com.bonobono.data.model.map.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CatchCharacterResponse(
    val charLatitude: Double,
    val charLongtitude: Double,
    val locationCharId: Int,
    val locationName: String,
    val ourCharacter: OurCharacterResponse
) : Parcelable