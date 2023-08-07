package com.bonobono.presentation.ui.game

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.mission.Mission
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.game.component.PromptOXButtonRow
import com.bonobono.presentation.ui.game.component.QuizPromptBox
import com.bonobono.presentation.ui.main.component.GifLoader
import com.bonobono.presentation.viewmodel.MissionViewModel

@Composable
fun QuizScreen(quizViewModel: MissionViewModel = hiltViewModel()) {
    LaunchedEffect(Unit) {
        quizViewModel.getOXQuiz(1)
    }

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
        QuizPromptBox(
            name = "클로버 요정",
            content = "오늘도 지구를 지켜줘서 고마워!\n\n내가 내는 문제를 맞추면 동물 친구들을 성장 시킬 수 있어!\n오늘도 화이팅!",
            modifier = Modifier.align(Alignment.BottomCenter),
        ) {
            PromptOXButtonRow(
                modifier = Modifier.padding(12.dp),
                onClickX = { /*TODO*/ },
                onClickO = {}
            )
        }

        val state by quizViewModel.oxQuizState.collectAsStateWithLifecycle()

        when (state) {
            is NetworkResult.Loading -> {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }

            is NetworkResult.Success -> {
                val oxQuiz = (state as NetworkResult.Success<Mission>).data
                val text = oxQuiz.problem
                Text(text = text)
            }

            is NetworkResult.Error -> {
                Log.d("quiz", "QuizScreen: ${(state as NetworkResult.Error).message}")
            }
        }
    }
}
