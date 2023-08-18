package com.bonobono.data.mapper

import com.bonobono.data.model.map.response.CatchCharacterResponse
import com.bonobono.data.model.map.response.LocationResponse
import com.bonobono.data.model.map.response.OurCharacterResponse
import com.bonobono.domain.model.map.CatchCharacter
import com.bonobono.domain.model.map.Location
import com.bonobono.domain.model.map.OurCharacter

fun LocationResponse.toDomain() : Location {
    return Location(
        centerLatitude, centerLongitude, id, name
    )
}

fun OurCharacterResponse.toDomain() : OurCharacter {
    return OurCharacter(
        charOrdId, description, id, level, name
    )
}

fun CatchCharacterResponse.toDomain() : CatchCharacter {
    return CatchCharacter(
        charLatitude, charLongtitude, locationCharId, locationName, ourCharacter.toDomain()
    )
}
