package com.bonobono.data.model.registration.request

data class PasswordChangeRequest(
    val newPassword: String,
    val password: String
)