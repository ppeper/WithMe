package com.bonobono.domain.model.notification

import java.time.LocalDateTime

data class Notification(
    var id: Int = 0,
    val title: String,
    val body: String,
    val receiveTime: String
)
