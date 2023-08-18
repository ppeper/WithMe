package com.bonobono.domain.model.notification

data class Notification(
    var id: Int = 0,
    var type: String,
    var articleId: String,
    val title: String,
    val body: String,
    val receiveTime: String
)
