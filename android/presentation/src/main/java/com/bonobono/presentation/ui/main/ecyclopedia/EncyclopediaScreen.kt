package com.bonobono.presentation.ui.main.ecyclopedia

import android.util.Log
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.bonobono.domain.model.character.OurCharacter
import com.bonobono.domain.model.character.UserCharacter
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.common.topbar.screen.EncyclopediaScreen
import com.bonobono.presentation.ui.main.component.AnimatedProfile
import com.bonobono.presentation.ui.main.component.BlindProfilePhoto
import com.bonobono.presentation.ui.main.component.ProfilePhoto
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.utils.Constants
import com.bonobono.presentation.utils.characterList
import com.bonobono.presentation.viewmodel.CharacterViewModel
import com.bonobono.presentation.viewmodel.MissionViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

// 현재 대표 동물 이미지로
private const val TAG = "EncyclopediaScreen"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EncyclopediaScreen(
    characterViewModel: CharacterViewModel = hiltViewModel(),
    missionViewModel: MissionViewModel = hiltViewModel(),
    navController: NavHostController
) {
    LaunchedEffect(key1 = Unit) {
        EncyclopediaScreen.buttons
            .onEach { button ->
                when (button) {
                    EncyclopediaScreen.AppBarIcons.NavigationIcon -> {
                        navController.popBackStack()
                    }
                }
            }.launchIn(this)
    }

    val userCharacterList by characterViewModel.userCharacterList.collectAsState()
    val ourCharacterList by characterViewModel.ourCharacterList.collectAsState()
    val mainCharacter by characterViewModel.character.collectAsState()
    val changeMainCharacter by characterViewModel.changeMainCharacter.collectAsState()

    var initId = missionViewModel.getLong(Constants.MAIN_CHARACTER)
    if (initId.toInt() == 0) initId = 12
    var selectedId = remember {
        mutableStateOf(initId)
    }
    var selectedCharacter = remember {
        mutableStateOf(mainCharacter)
    }


    val coroutineScope = rememberCoroutineScope()
    val snackBarHostState = remember {
        SnackbarHostState()
    }

    LaunchedEffect(Unit) {
        characterViewModel.getUserCharacterList()
        characterViewModel.getOurCharacterList()
        characterViewModel.getMainCharacter()

    }
    val image =
        characterList.find { it.id == selectedCharacter.value.char_ord_id }?.icon ?: R.drawable.ic_profile
    Scaffold(
        snackbarHost = { SnackbarHost(snackBarHostState) }
    ) { it ->
        it
        Column(
            Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = Modifier) {
                AnimatedProfile(
                    profileImage = image,
                    source = R.raw.animation_card
                )
                if (userCharacterList.find { character -> character.id.toLong() == selectedId.value } != null) {
                    ElevatedFilterChip(
                        modifier = Modifier
                            .padding(12.dp)
                            .align(Alignment.TopEnd),
                        selected = false,
                        onClick = {
                            characterViewModel.patchMainCharacter(selectedId.value.toInt())
                            coroutineScope.launch {
                                snackBarHostState.showSnackbar("대표 캐릭터 변경!")
                            }
                        },
                        label = {
                            Text(
                                text = "대표 캐릭터 설정",
                                style = CustomTextStyle.gameGuideTextStyle
                            )
                        })
                }
            }
            CurInformation(selectedCharacter = selectedCharacter)
            Spacer(modifier = Modifier.size(12.dp))
            UserCharacters(userCharacterList, selectedId, selectedCharacter)
            OurCharacters(ourCharacterList, selectedId)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CurInformation(
    selectedCharacter: MutableState<UserCharacter>
) {

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
            text = "이름: ${selectedCharacter.value.custom_name}\n레벨: ${selectedCharacter.value.level}\t\tExp: ${selectedCharacter.value.experience}\n${selectedCharacter.value.description}",
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp)
                .align(Alignment.Center),
            style = CustomTextStyle.quizContentStyle
        )
    }
}

@Composable
fun UserCharacters(userCharacterList: List<UserCharacter>, selectedId: MutableState<Long>, selectedCharacter: MutableState<UserCharacter>) {
    Card(
        modifier = Modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(80.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .heightIn(max = 700.dp)
                .wrapContentHeight(),
            userScrollEnabled = false
        ) {
            // 보유중 / 아닌 것들 나눠서 표시
            items(userCharacterList) { item ->
                ProfilePhoto(
                    profileImage = characterList.find { it.id == item.char_ord_id }!!.icon,
                    modifier = Modifier
                        .clickable {
                            selectedId.value = item.id.toLong()
                            selectedCharacter.value = item

                            Log.d(TAG, "UserCharacters: ${item.char_ord_id}")
                            Log.d(TAG, "UserCharacters: ${selectedCharacter.value}")
                        }.border(BorderStroke(1.dp, DarkGray), shape = CircleShape)
                )
            }
        }
    }
}

@Composable
fun OurCharacters(ourCharacterList: List<OurCharacter>, selectedId: MutableState<Long>) {
    Card(
        modifier = Modifier.padding(12.dp),
        elevation = CardDefaults.cardElevation(4.dp),
        colors = CardDefaults.cardColors(containerColor = White)
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(100.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier
                .heightIn(max = 700.dp)
                .wrapContentHeight(),
            userScrollEnabled = false
        ) {
            // 보유중 / 아닌 것들 나눠서 표시
            items(ourCharacterList) { item ->
                characterList.find { it.name == item.name }?.let {
                    BlindProfilePhoto(it.icon, Modifier)
                }
            }
        }
    }
}