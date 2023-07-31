package com.bonobono.presentation.ui.main

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bonobono.presentation.ui.component.CharacterProfile
import com.bonobono.presentation.ui.component.HeaderTwoText
import com.bonobono.presentation.ui.component.MissionCard
import com.bonobono.presentation.ui.component.ProgressBar

@Composable
fun MissionScreen(navController: NavController) {
    Column(
        Modifier
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.size(12.dp))
        CharacterProfile {
            ProgressBar(icon = Icons.Filled.AccountBox, title = "제목", percent = 0.3)
        }
        Spacer(modifier = Modifier.size(12.dp))
        DailyMission()
    }
}

@Composable
fun DailyMission() {
    val items = listOf<String>(
        "1", "2", " 3"
    )
    HeaderTwoText(text = "일일 미션")
    Spacer(modifier = Modifier.padding(8.dp))
    Column(
        Modifier.padding(vertical = 4.dp)
    ) {
        items.take(3).forEach {
            MissionCard()
        }
    }
}
