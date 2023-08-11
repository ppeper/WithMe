package com.bonobono.data.mapper

import com.bonobono.data.model.map.response.LocationResponse
import com.bonobono.domain.model.map.Location

fun LocationResponse.toDomain() : Location {
    return Location(
        centerLatitude, centerLongitude, id, name
    )
}