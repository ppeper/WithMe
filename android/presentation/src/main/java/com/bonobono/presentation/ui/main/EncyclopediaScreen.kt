package com.bonobono.presentation.ui.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.ui.component.CharacterBlind
import com.bonobono.presentation.ui.component.CharacterProfile
import com.bonobono.presentation.ui.component.EncyclopediaCard
import com.bonobono.presentation.ui.component.HeaderTwoText
import com.bonobono.presentation.ui.component.MissionCard
import com.bonobono.presentation.ui.component.ProgressBar

@Composable
fun EncyclopediaScreen() {
    Column(
        Modifier
            .padding(horizontal = 12.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.size(12.dp))
        CharacterProfile { }
        Spacer(modifier = Modifier.size(12.dp))
        KnownCharacter()
        UnknownCharacter()
    }
}

@Composable
fun KnownCharacter() {
    val items = listOf<String>(
        "1", "2", " 3"
    )
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End
    ) {
        HeaderTwoText(text = "전체보기")
    }
    Column(
        Modifier.padding(vertical = 4.dp)
    ) {
        items.take(3).forEach {
            EncyclopediaCard()
        }
    }
}

@Composable
fun UnknownCharacter() {
    val items = listOf<String>(
        "1", "2", " 3"
    )
    Spacer(modifier = Modifier.size(4.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.Center
    ) {
        HeaderTwoText(text = "기다리고 있는 친구들이 있어요!")
    }
    Spacer(modifier = Modifier.size(4.dp))
    LazyRow(
        Modifier.padding(vertical = 4.dp)
    ) {
        items(items.size) {
            CharacterBlind()
        }
    }
}
