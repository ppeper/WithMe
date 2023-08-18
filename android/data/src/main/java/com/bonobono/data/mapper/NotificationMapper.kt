package com.bonobono.data.mapper

import com.bonobono.data.model.notification.NotificationEntity
import com.bonobono.domain.model.notification.Notification
import java.time.LocalDateTime

fun NotificationEntity.toDomain(): Notification =
    Notification(
        id = id,
        type = type,
        articleId = articleId,
        title = title,
        body = body,
        receiveTime = receiveTime
    )

fun Notification.toModel(): NotificationEntity =
    NotificationEntity(
        type = type,
        articleId = articleId,
        title = title,
        body = body,
        receiveTime = receiveTime
    )