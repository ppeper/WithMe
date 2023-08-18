package com.bonobono.domain.usecase.notification

import com.bonobono.domain.model.notification.Notification
import com.bonobono.domain.repository.NotificationRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    operator fun invoke(): Flow<List<Notification>> {
        return notificationRepository.getAllNotifications()
    }
}