package com.bonobono.data.model.notification

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notifications")
data class NotificationEntity (
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    val type: String,
    val articleId: String,
    val title: String,
    val body: String,
    val receiveTime: String
)

