package com.bonobono.domain.model.registration

data class Token (
    val accessToken : String,
    val refreshToken : String
)