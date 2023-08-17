package com.bonobono.presentation.ui.main

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
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


private const val TAG = "HomeScreen"

@Composable
fun MainHomeScreen(
    characterViewModel: CharacterViewModel = hiltViewModel(),
) {

    LaunchedEffect(Unit) {
        characterViewModel.getUserCharacterList()
        characterViewModel.getMainCharacter()
    }

    val userCharacterList by characterViewModel.userCharacterList.collectAsState()
    val mainCharacter by characterViewModel.character.collectAsState()

    Log.d(TAG, "MainHomeScreen: $userCharacterList")
    Log.d(TAG, "MainHomeScreen: $mainCharacter")
    Box(modifier = Modifier.fillMaxSize()) {
        //LottieLoader(source = R.raw.main_background, modifier = Modifier.fillMaxHeight())
        Image(
            painter = painterResource(id = R.drawable.background_main2),
            contentDescription = "배경",
            modifier = Modifier.fillMaxSize().fillMaxWidth().fillMaxHeight(),
            contentScale = ContentScale.FillWidth
        )
        GifLoader(
            modifier = Modifier
                .align(Alignment.Center)
                .size(240.dp)
                .padding(12.dp),
            source = getCharacterAnimation(mainCharacter.char_ord_id, mainCharacter.level, "happy")
        )
    }
}

