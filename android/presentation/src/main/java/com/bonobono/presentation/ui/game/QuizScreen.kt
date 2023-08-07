package com.bonobono.presentation.ui.game

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.game.component.QuizTextBox
import com.bonobono.presentation.ui.main.component.GifLoader

@Composable
fun QuizScreen() {
    Box(modifier = Modifier.fillMaxSize()) {

        Image(
            painter = painterResource(id = R.drawable.background_quiz),
            contentDescription = "배경",
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillHeight
        )

        GifLoader(
            modifier = Modifier
                .fillMaxWidth()
                .height(360.dp)
                .align(Alignment.TopCenter), source = R.raw.animation_fairy
        )
        QuizTextBox(
            name = "클로버 요정",
            content = "오늘도 지구를 지켜줘서 고마워!\n\n내가 내는 문제를 맞추면 동물 친구들을 성장 시킬 수 있어!\n오늘도 화이팅!",
            modifier = Modifier.align(Alignment.BottomCenter)
        )
    }
}