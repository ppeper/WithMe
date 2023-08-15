package com.bonobono.data.mapper

import com.bonobono.data.model.notification.NotificationEntity
import com.bonobono.domain.model.notification.Notification
import java.time.LocalDateTime

fun NotificationEntity.toDomain(): Notification =
    Notification(
        id = id,
        title = title,
        body = body,
        receiveTime = LocalDateTime.now().toString()
    )

fun Notification.toModel(): NotificationEntity =
    NotificationEntity(
        id = -1,
        title = title,
        body = body,
        receiveTime = receiveTime
    )