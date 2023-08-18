package com.bonobono.domain.model.map

data class Campaign(
    val authority: String,
    val completionStatus: Boolean,
    val endDate: String,
    val locationName: String = "",
    val name: String,
    val startDate: String,
    val url: String,
    val locationId: Long = 0
)