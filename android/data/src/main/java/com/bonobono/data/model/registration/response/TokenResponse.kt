package com.bonobono.data.model.registration.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokenResponse(
    val accessToken: String,
    val accessTokenExpiresIn: Long,
    val grantType: String,
    val refreshToken: String
) : Parcelable