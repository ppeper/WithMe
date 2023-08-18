package com.bonobono.domain.usecase.notification

import com.bonobono.domain.repository.NotificationRepository
import javax.inject.Inject

class DeleteNotificationUseCase @Inject constructor(
    private val notificationRepository: NotificationRepository
) {
    suspend operator fun invoke(id: Int) = notificationRepository.deleteNotification(id)
}