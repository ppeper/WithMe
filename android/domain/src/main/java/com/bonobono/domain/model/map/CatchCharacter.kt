package com.bonobono.domain.model.map

data class CatchCharacter (
    val charLatitude: Double,
    val charLongtitude: Double,
    val locationCharId: Int,
    val locationName: String,
    val ourCharacter: OurCharacter
)