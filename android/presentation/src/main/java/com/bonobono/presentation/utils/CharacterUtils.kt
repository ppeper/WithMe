package com.bonobono.presentation.utils

import androidx.annotation.DrawableRes
import com.bonobono.presentation.R

sealed class Character(
    val name: String,
    @DrawableRes val icon: Int,
    val content: String,
    val level: Int
) {
    object AMMONITE : Character(CharacterName.AMMONITE, CharacterImage.AMMONITE, CharacterContent.AMMONITE, 1)
    object BELUGA : Character(CharacterName.BELUGA, CharacterImage.BELUGA, CharacterContent.BELUGA, 1)
    object HIPPOCAMPUS : Character(CharacterName.HIPPOCAMPUS, CharacterImage.HIPPOCAMPUS, CharacterContent.HIPPOCAMPUS, 1)
    object KILLER_WHALE : Character(CharacterName.KILLER_WHALE, CharacterImage.KILLER_WHALE, CharacterContent.KILLER_WHALE, 1)
    object NEMO : Character(CharacterName.NEMO, CharacterImage.NEMO, CharacterContent.NEMO, 1)
    object OTTER : Character(CharacterName.OTTER, CharacterImage.OTTER, CharacterContent.OTTER, 1)
    object SEA_LION : Character(CharacterName.SEA_LION, CharacterImage.SEA_LION, CharacterContent.SEA_LION, 1)
    object SEA_GULL : Character(CharacterName.SEA_GULL, CharacterImage.SEA_GULL, CharacterContent.SEA_GULL, 1)
    object SHRIMP : Character(CharacterName.SHRIMP, CharacterImage.SHRIMP, CharacterContent.SHRIMP, 1)
    object TURTLE : Character(CharacterName.TURTLE, CharacterImage.TURTLE, CharacterContent.TURTLE, 1)
}

object CharacterName {
    const val AMMONITE = "암모나이트"
    const val BELUGA = "벨루가"
    const val HIPPOCAMPUS = "해마"
    const val KILLER_WHALE = "범고래"
    const val NEMO = "니모"
    const val OTTER = "해달"
    const val SEA_LION = "바다사자"
    const val SEA_GULL = "갈매기"
    const val SHRIMP = "새우"
    const val TURTLE = "거북이"
}

object CharacterImage {
    val AMMONITE = R.drawable.character_ammonite
    val BELUGA = R.drawable.character_beluga
    val HIPPOCAMPUS = R.drawable.character_hippocampus
    val KILLER_WHALE = R.drawable.character_killer_whale
    val NEMO = R.drawable.character_nemo
    val OTTER = R.drawable.character_otter
    val SEA_LION = R.drawable.character_sea_lion
    val SEA_GULL = R.drawable.character_seagull
    val SHRIMP = R.drawable.character_shrimp
    val TURTLE = R.drawable.character_turtle
}

object CharacterContent {
    const val AMMONITE = "암모나이트"
    const val BELUGA = "벨루가"
    const val HIPPOCAMPUS = "해마"
    const val KILLER_WHALE = "범고래"
    const val NEMO = "니모"
    const val OTTER = "해달"
    const val SEA_LION = "바다사자"
    const val SEA_GULL = "갈매기"
    const val SHRIMP = "새우"
    const val TURTLE = "거북이"
}

