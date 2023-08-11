package com.bonobono.data.model.map.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LocationResponse(
    val centerLatitude: Double,
    val centerLongitude: Double,
    val id: Long,
    val name: String
) : Parcelable