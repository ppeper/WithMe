package com.bonobono.data.model.registration.request

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MemberNicknameRequest (
    val nickname : String
) : Parcelable