package com.bonobono.presentation.utils

import java.time.LocalDateTime
import java.time.temporal.ChronoUnit

object DateUtils {
    private const val MINUTE = 60L
    private const val HOUR = 3600L
    private const val DAY = 86400L
    private const val MONTH = 2592000L
    private const val YEAR = 31536000L

    fun dateToString(upload: LocalDateTime): String {
        val currentTime = LocalDateTime.now()
        val timeDifference = ChronoUnit.SECONDS.between(upload, currentTime)

        return when {
            timeDifference < MINUTE -> "방금 전"
            timeDifference < HOUR -> "${timeDifference / MINUTE}분 전"
            timeDifference < DAY -> "${timeDifference / HOUR}시간 전"
            timeDifference < MONTH -> "${timeDifference / DAY}일 전"
            timeDifference < YEAR -> "${timeDifference / MONTH}달 전"
            else -> "${timeDifference / YEAR}년 전"
        }
    }
}