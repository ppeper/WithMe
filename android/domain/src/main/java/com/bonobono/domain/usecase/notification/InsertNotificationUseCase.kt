package com.bonobono.domain.usecase.notification

import com.bonobono.domain.model.notification.Notification
import com.bonobono.domain.repository.NotificationRepository
import javax.inject.Inject

class InsertNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(notification: Notification) = notificationRepository.insertNotification(notification)
}