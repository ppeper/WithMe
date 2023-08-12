package com.bonobono.data.remote

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class FCMService : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("FCM Token", token)
        // 토큰을 서버로 전송 또는 필요한 처리 수행
    }
}