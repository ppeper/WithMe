package com.bonobono.data.model.mission.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MissionResponse(
    val problemId: Int,
    val answer: String,
    val choices: List<ChoiceResponse>,
    val commentary: String,
    val problem: String
) : Parcelable