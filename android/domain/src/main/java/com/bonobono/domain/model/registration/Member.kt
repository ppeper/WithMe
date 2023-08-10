package com.bonobono.domain.model.registration

data class Member(
    val authoritySet: List<AuthoritySet>,
    val name: String,
    val nickname: String,
    val phoneNumber: String,
    val username: String
)