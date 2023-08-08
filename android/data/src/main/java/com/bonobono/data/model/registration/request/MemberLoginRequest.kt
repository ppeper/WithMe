package com.bonobono.data.model.registration.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberLoginRequest (
    val username : String,
    val password : String
) : Parcelable