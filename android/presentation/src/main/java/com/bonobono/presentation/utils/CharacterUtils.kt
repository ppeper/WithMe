package com.bonobono.presentation.utils

import androidx.annotation.DrawableRes
import com.bonobono.presentation.R

sealed class Character(
    val name: String,
    @DrawableRes val icon: Int,
    val id: Int
) {
    object WHALE : Character(CharacterName.WHALE, CharacterImage.WHALE, 11)
    object BELUGA : Character(CharacterName.BELUGA, CharacterImage.BELUGA, 2)
    object HIPPOCAMPUS : Character(CharacterName.HIPPOCAMPUS, CharacterImage.HIPPOCAMPUS, 3)
    object KILLER_WHALE : Character(CharacterName.KILLER_WHALE, CharacterImage.KILLER_WHALE, 4)
    object NEMO : Character(CharacterName.NEMO, CharacterImage.NEMO, 5)
    object OTTER : Character(CharacterName.OTTER, CharacterImage.OTTER, 6)
    object SEA_GULL : Character(CharacterName.SEA_GULL, CharacterImage.SEA_GULL, 8)
    object SHRIMP : Character(CharacterName.SHRIMP, CharacterImage.SHRIMP, 9)
    object TURTLE : Character(CharacterName.TURTLE, CharacterImage.TURTLE, 10)
    object DOLPHIN : Character(CharacterName.DOLPHIN, CharacterImage.DOLPHIN, 12)
    object SEA_ELEPHANT : Character(CharacterName.SEA_ELEPHANT, CharacterImage.SEA_ELEPHANT, 13)
    object PENGUIN : Character(CharacterName.PENGUIN, CharacterImage.PENGUIN, 1)
}

object CharacterName {
    const val BELUGA = "벨루가(흰 돌고래)"
    const val HIPPOCAMPUS = "해마"
    const val KILLER_WHALE = "범고래"
    const val NEMO = "니모"
    const val OTTER = "해달"
    const val SEA_GULL = "갈매기"
    const val SHRIMP = "새우"
    const val TURTLE = "거북"
    const val WHALE = "고래"
    const val DOLPHIN = "돌고래"
    const val SEA_ELEPHANT = "바다코끼리"
    const val PENGUIN = "펭귄"
}

object CharacterImage {
    val BELUGA = R.drawable.ic_beluga
    val HIPPOCAMPUS = R.drawable.ic_sea_horse
    val KILLER_WHALE = R.drawable.ic_killer_whale
    val NEMO = R.drawable.ic_nemo
    val OTTER = R.drawable.ic_sea_otter
    val SEA_ELEPHANT = R.drawable.ic_sea_elephant
    val SEA_GULL = R.drawable.ic_sea_gull
    val SHRIMP = R.drawable.ic_shrimp
    val TURTLE = R.drawable.ic_turtle
    val DOLPHIN = R.drawable.ic_dolphin
    val WHALE = R.drawable.ic_whale
    val PENGUIN = R.drawable.ic_penguin
}

val characterList = listOf<Character>(
    Character.DOLPHIN,
    Character.WHALE,
    Character.SEA_ELEPHANT,
    Character.BELUGA,
    Character.HIPPOCAMPUS,
    Character.KILLER_WHALE,
    Character.NEMO,
    Character.OTTER,
    Character.SEA_GULL,
    Character.SHRIMP,
    Character.TURTLE,
    Character.PENGUIN
)

fun getDolphinAnimation(level: String, state: String): Int {
    return when (level) {
        "LEVEL_1" -> {
            when (state) {
                "sad" -> R.raw.dolphin_lv1_sad
                "happy" -> R.raw.dolphin_lv1_happy
                "normal" -> R.raw.dolphin_lv1_normal
                else -> -1
            }
        }
        "LEVEL_2" -> {
            when (state) {
                "sad" -> R.raw.dolphin_lv2_sad
                "happy" -> R.raw.dolphin_lv2_happy
                "normal" -> R.raw.dolphin_lv3_normal
                else -> -1
            }
        }
        "LEVEL_3" -> {
            when (state) {
                "sad" -> R.raw.dolphin_lv3_sad
                "happy" -> R.raw.dolphin_lv3_happy
                "normal" -> R.raw.dolphin_lv3_normal
                else -> -1
            }
        } else -> -1
    }
}

fun getWhaleAnimation(level: String, state: String): Int {
    return when (level) {
        "LEVEL_1" -> {
            when (state) {
                "sad" -> R.raw.whale_lv1_sad
                "happy" -> R.raw.whale_lv1_happy
                "normal" -> R.raw.whale_lv1_normal
                else -> -1
            }
        }
        "LEVEL_2" -> {
            when (state) {
                "sad" -> R.raw.whale_lv2_sad_edit
                "happy" -> R.raw.whale_lv2_happy
                "normal" -> R.raw.whale_lv2_normal
                else -> -1
            }
        }
        "LEVEL_3" -> {
            when (state) {
                "sad" -> R.raw.whale_lv3_sad
                "happy" -> R.raw.whale_lv3_happy
                "normal" -> R.raw.whale_lv3_normal
                else -> -1
            }
        } else -> -1
    }
}

fun getSeaElephantAnimation(level: String, state: String): Int {
    return when (level) {
        "LEVEL_1" -> {
            when (state) {
                "sad" -> R.raw.sealion_lv1_sad
                "happy" -> R.raw.sealion_lv1_happy
                "normal" -> R.raw.sealion_lv1_normal
                else -> -1
            }
        }
        "LEVEL_2" -> {
            when (state) {
                "sad" -> R.raw.sealion_lv2_sad
                "happy" -> R.raw.sealion_lv2_happy
                "normal" -> R.raw.sealion_lv2_normal
                else -> -1
            }
        }
        "LEVEL_3" -> {
            when (state) {
                "sad" -> R.raw.sealion_lv3_sad
                "happy" -> R.raw.sealion_lv3_happy
                "normal" -> R.raw.sealion_lv3_normal
                else -> -1
            }
        } else -> -1
    }
}

fun getCharacterAnimation(id: Int, level: String, state: String) : Int {
    return when(id) {
        11 -> { getWhaleAnimation(level, state) }
        12 -> { getDolphinAnimation(level, state) }
        else -> getSeaElephantAnimation(level, state)
    }
}