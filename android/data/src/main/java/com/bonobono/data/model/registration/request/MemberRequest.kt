package com.bonobono.data.model.registration.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberRequest (
    val memeberId : Long,
    val username : String,
    val password : String,
    val passwordCheck : String,
    val name : String,
    val nickname : String,
    val phonNumber : String
) : Parcelable