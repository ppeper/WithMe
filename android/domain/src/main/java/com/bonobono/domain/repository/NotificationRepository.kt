package com.bonobono.domain.repository

import com.bonobono.domain.model.notification.Notification
import kotlinx.coroutines.flow.Flow

interface NotificationRepository {
    fun getAllNotifications() : Flow<List<Notification>>
    suspend fun getNotification(id: Long) : Notification
    suspend fun insertNotification(notification: Notification)
    suspend fun deleteNotification(id: Int)
}