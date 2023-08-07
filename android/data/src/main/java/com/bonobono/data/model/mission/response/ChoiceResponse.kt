package com.bonobono.data.model.mission.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class ChoiceResponse(
    val content: String,
    val id: Int
) : Parcelable