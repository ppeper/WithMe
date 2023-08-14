package com.bonobono.data.model.map.response

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CampaignResponse(
    val authority: String,
    val completionStatus: Boolean,
    val endDate: String,
    val locationName: String,
    val name: String,
    val startDate: String,
    val url: String
) : Parcelable