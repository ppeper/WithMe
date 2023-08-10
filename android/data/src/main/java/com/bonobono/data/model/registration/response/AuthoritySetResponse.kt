package com.bonobono.data.model.registration.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class AuthoritySetResponse(
    val role: String
) : Parcelable