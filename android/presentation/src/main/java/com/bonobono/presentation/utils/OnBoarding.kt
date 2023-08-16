package com.bonobono.presentation.utils

import androidx.annotation.DrawableRes
import com.bonobono.presentation.R

sealed class OnBoarding(
    val content: String,
    @DrawableRes val animation: Int,
) {
    object AR : OnBoarding(OnBoardingContent.AR, OnBoardingAnimation.AR)
    object MISSION : OnBoarding(OnBoardingContent.MISSION, OnBoardingAnimation.MISSION)
    object CAMPAIGN : OnBoarding(OnBoardingContent.CAMPAIGN, OnBoardingAnimation.CAMPAIGN)
    object COMMUNITY : OnBoarding(OnBoardingContent.COMMUNITY, OnBoardingAnimation.COMMUNITY)
}

object OnBoardingContent {
    const val AR = "캠페인에 참여하며\n나만의 바다 동물 친구들을 수집해!"
    const val MISSION = "매일 주어지는 일일 미션을 해결해서\n동물 친구를 성장해!"
    const val CAMPAIGN = "해변별 캠페인 정보를\n모두 모아\n한눈에 확인해! "
    const val COMMUNITY = "커뮤니티와 채팅으로\n다른 사람들과 함께해!"
}

object OnBoardingAnimation {
    val AR = R.raw.animation_four_quiz_card
    val MISSION = R.raw.onboarding_mission
    val CAMPAIGN = R.raw.onboarding_campaign
    val COMMUNITY = R.raw.onboarding_community
}