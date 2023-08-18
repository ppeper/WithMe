package com.bonobono.data.model.registration.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class LoginRequest(
    val fcmtoken: String,
    val password: String,
    val username: String
) : Parcelable