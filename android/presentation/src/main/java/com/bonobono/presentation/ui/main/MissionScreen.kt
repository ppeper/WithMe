package com.bonobono.presentation.ui.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bonobono.presentation.ui.component.HeaderTwoText
import com.bonobono.presentation.ui.component.MissionCard

@Composable
fun MissionScreen(navController: NavController) {
    Column(
        Modifier.padding(horizontal = 12.dp)
    ) {
        HeaderMission()
        DailyMission()
    }
}

@Composable
fun HeaderMission() {
    Column {

    }
}

@Composable
fun DailyMission() {
    val items = listOf<String>(
        "1", "2", " 3"
    )
    HeaderTwoText(text = "일일 미션")
    Spacer(modifier = Modifier.padding(8.dp))
    LazyColumn(
        Modifier.padding(vertical = 4.dp)
    ) {
        items(items.size) {
            MissionCard()
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    HeaderMission()
}