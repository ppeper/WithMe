package com.bonobono.domain.repository

interface SharedLocalRepository {

    fun getMemberId(key: String): Int
    fun getRole(key: String): String?
}