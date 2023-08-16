package com.bonobono.presentation.ui.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.OnBoardingNav
import com.bonobono.presentation.ui.common.GifLoader
import com.bonobono.presentation.ui.common.LottieLoader
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.utils.getCharacterAnimation
import com.bonobono.presentation.viewmodel.CharacterViewModel
import com.bonobono.presentation.viewmodel.MainViewModel
import com.bonobono.presentation.viewmodel.MissionViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainHomeScreen(
    navController: NavHostController,
    missionViewModel: MissionViewModel = hiltViewModel(),
    characterViewModel: CharacterViewModel = hiltViewModel(),
    mainViewModel: MainViewModel = hiltViewModel()
) {

    var mainId = missionViewModel.getLong(Constants.MAIN_CHARACTER)
    if(mainId.toInt() == 0) {
        mainId = 12
    }
    LaunchedEffect(Unit) {
        characterViewModel.getCharacter(mainId.toInt())
    }
    val curMainCharacter by characterViewModel.character.collectAsState()

    Box(modifier = Modifier.fillMaxSize()) {
        LottieLoader(source = R.raw.main_background, modifier = Modifier.fillMaxHeight())
        GifLoader(
            modifier = Modifier
                .align(Alignment.Center)
                .size(240.dp)
                .padding(12.dp)
                .clip(CircleShape)
                .background(White),
            source = getCharacterAnimation(curMainCharacter.char_ord_id, curMainCharacter.level, "happy")
        )
    }
}

@Preview
@Composable
fun Preview() {
    MainHomeScreen(navController = rememberNavController())
}

