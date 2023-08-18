package com.bonobono.data.model.registration.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TokenRequest(
    val accessToken: String,
    val refreshToken: String
) : Parcelable