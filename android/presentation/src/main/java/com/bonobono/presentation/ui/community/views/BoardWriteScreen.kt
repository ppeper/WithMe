package com.bonobono.presentation.ui.community.views

import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.text.selection.SelectionContainer
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.bonobono.presentation.ui.NavigationRouteName
import com.bonobono.presentation.ui.common.topbar.SharedTopAppBar
import com.bonobono.presentation.ui.common.topbar.rememberAppBarState
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.TextGray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BoardWriteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    val appBarState = rememberAppBarState(navController = navController)
    Scaffold(
        topBar = { SharedTopAppBar(appBarState = appBarState) },
        bottomBar = {
            BoardWriteBottomView(onPhotoClick = { navController.navigate(NavigationRouteName.GALLERY) })
        }
    ) { innerPaddings ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPaddings)
        ) {
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
            ) {

                val titleTextState = remember { mutableStateOf("") }
                val contentTextState = remember { mutableStateOf("") }

                Column(
                    modifier = modifier.padding(vertical = 32.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    SelectionContainer {
                        BasicTextField(
                            value = titleTextState.value,
                            onValueChange = { titleTextState.value = it },
                            textStyle = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 18.sp,
                                fontWeight = FontWeight(700),
                                color = Black_100,
                            ),
                            singleLine = true,
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                            decorationBox = { innerTextField ->
                                if (titleTextState.value.isEmpty()) {
                                    Text(
                                        text = "제목 추가",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            lineHeight = 18.sp,
                                            fontWeight = FontWeight(700),
                                            color = TextGray,
                                        ),
                                    )
                                }
                                innerTextField()
                            }
                        )
                    }
                    SelectionContainer {
                        BasicTextField(
                            value = contentTextState.value,
                            onValueChange = { contentTextState.value = it },
                            textStyle = TextStyle(
                                fontSize = 16.sp,
                                fontWeight = FontWeight(400),
                                color = Black_100,
                            ),
                            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                            decorationBox = { innerTextField ->
                                if (contentTextState.value.isEmpty()) {
                                    Text(
                                        text = "자유롭게 글을 작성해주세요.",
                                        style = TextStyle(
                                            fontSize = 16.sp,
                                            fontWeight = FontWeight(400),
                                            color = TextGray,
                                        ),
                                    )
                                }
                                innerTextField()
                            }
                        )
                    }
                    PhotoSelectedListView(
                        selectedPhotos = DummyData.selectedPhotos
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Preview() {
    BoardWriteScreen(navController = rememberNavController())
}