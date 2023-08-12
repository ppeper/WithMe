package com.bonobono.presentation.ui.main.ecyclopedia

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.main.component.AnimatedProfile
import com.bonobono.presentation.ui.main.component.ProfilePhoto
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Character
import com.bonobono.presentation.utils.characterList
import com.bonobono.presentation.viewmodel.CharacterViewModel

// 현재 대표 동물 이미지로
@Composable
fun EncyclopediaScreen(characterViewModel: CharacterViewModel = hiltViewModel()) {
    val userCharacterList by characterViewModel.userCharacterList.collectAsState()

    Column(
        Modifier
    ) {
        AnimatedProfile(
            profileImage = R.drawable.beluga_whale,
            source = R.raw.animation_card
        )
        CurInformation()
        Spacer(modifier = Modifier.size(12.dp))
        Characters(userCharacterList)
    }
}

@Composable
fun CurInformation() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ) {
        Image(
            painter = painterResource(id = R.drawable.img_pixel_chat),
            contentDescription = null,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
        )
        Text(
            text = "돌고래 2세",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center)
        )
    }
}

@Composable
fun Characters(userCharacterList: List<UserCharacter>) {
    Card(
        modifier = Modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(80.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp)
        ) {
            // 보유중 / 아닌 것들 나눠서 표시
            items(userCharacterList) {
                ProfilePhoto(
                    profileImage = characterList[it.id].icon, modifier = Modifier
                        .size(64.dp)
                        .clip(CircleShape)
                        .background(LightGray)
                        .border(BorderStroke(1.dp, DarkGray), shape = CircleShape)
                )
            }
        }
    }
}