package com.bonobono.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bonobono.domain.model.notification.Notification
import com.bonobono.domain.usecase.notification.DeleteNotificationUseCase
import com.bonobono.domain.usecase.notification.GetAllNotificationUseCase
import com.bonobono.domain.usecase.notification.GetNotificationByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotificationViewModel @Inject constructor(
    private val getAllNotificationUseCase: GetAllNotificationUseCase,
    private val deleteNotificationUseCase: DeleteNotificationUseCase
): ViewModel() {

    init {
        fetchNotifications()
    }

    private val _notificationList = MutableStateFlow<List<Notification>>(emptyList())
    val notificationList = _notificationList.asStateFlow()

    private fun fetchNotifications() = viewModelScope.launch {
        getAllNotificationUseCase.invoke().collect {
            _notificationList.value = it
        }
    }

    fun deleteNotification(id: Int) = viewModelScope.launch {
        deleteNotificationUseCase.invoke(id)
    }
}