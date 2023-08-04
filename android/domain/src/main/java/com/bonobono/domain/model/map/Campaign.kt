package com.bonobono.domain.model.map

import java.util.Date

data class Campaign (
    val id: Int,
    val locationId: Int,
    val name: String,
    val startDate: Date,
    val endDate: Date,
    val isCompleted: Boolean,
    val agency: String
)