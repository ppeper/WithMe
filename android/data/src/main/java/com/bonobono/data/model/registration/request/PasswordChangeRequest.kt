package com.bonobono.data.model.registration.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class PasswordChangeRequest(
    val newPassword: String,
    val password: String
) : Parcelable