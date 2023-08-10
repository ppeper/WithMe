package com.bonobono.data.model.mission.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class TotalScoreResponse (
    val attendanceScore: Int,
    val totalScore: Int
) : Parcelable