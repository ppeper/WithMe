package com.bonobono.presentation.ui.map

import android.util.Log
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DateRangePicker
import androidx.compose.material3.DateRangePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ElevatedFilterChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDateRangePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.bonobono.domain.model.NetworkResult
import com.bonobono.domain.model.map.Campaign
import com.bonobono.presentation.ui.CommunityFab
import com.bonobono.presentation.ui.common.LoadingView
import com.bonobono.presentation.ui.community.views.board.ReportDropDown
import com.bonobono.presentation.ui.community.views.board.TopContentWrite
import com.bonobono.presentation.ui.community.views.link.LinkView
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.utils.DateUtils
import com.bonobono.presentation.viewmodel.CommunityViewModel
import com.bonobono.presentation.viewmodel.MapViewModel
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import java.time.Instant
import java.time.OffsetDateTime

private const val TAG = "CampaignWriteScreen"
@OptIn(ExperimentalPermissionsApi::class, ExperimentalComposeUiApi::class,
    ExperimentalMaterial3Api::class
)
@Composable
fun CampaignWriteScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    mapViewModel: MapViewModel = hiltViewModel(),
    communityViewModel: CommunityViewModel = hiltViewModel()
) {
    val route = navController.currentDestination?.route ?: CommunityFab.FREE.route
    val context = LocalContext.current
    val keyboardController = LocalSoftwareKeyboardController.current
    var showDialog by remember { mutableStateOf(false) }
    var titleTextState by rememberSaveable { mutableStateOf("") }
    var contentTextState by rememberSaveable { mutableStateOf("") }
    val selectedOcean by mapViewModel.selectedLocation.collectAsStateWithLifecycle()
    val locations by mapViewModel.locations.collectAsState()
    var isLoading by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        mapViewModel.getLocations()
    }

    val writeCampaignState by mapViewModel.writeCampaignState.collectAsStateWithLifecycle()
    LaunchedEffect(writeCampaignState) {
        when (writeCampaignState) {
            is NetworkResult.Success -> {
                Log.d(TAG, "CampaignWriteScreen: ")
                isLoading = false
                navController.popBackStack()
            }

            is NetworkResult.Loading -> {
                Log.d(TAG, "CampaignWriteScreen: loading")
                isLoading = true
            }

            is NetworkResult.Error -> {

            }
            else -> {
                Log.d(TAG, "CampaignWriteScreen: ")
            }
        }
    }

    val dateRangePickerState = rememberDateRangePickerState(
        initialSelectedStartDateMillis = Instant.now().toEpochMilli(),
        initialSelectedEndDateMillis = OffsetDateTime.now().plusDays(8).toInstant().toEpochMilli(),
        yearRange = IntRange(2023, 2100),
        initialDisplayMode = DisplayMode.Picker
    )

    var start by remember {
        mutableStateOf(DateUtils.convertMillisToYymmdd(dateRangePickerState.selectedStartDateMillis!!))
    }
    var end by remember {
        mutableStateOf(DateUtils.convertMillisToYymmdd(dateRangePickerState.selectedEndDateMillis!!))
    }

    if(showDialog) {
        SimpleDateRangePickerInDatePickerDialog(openDialog = showDialog, dateRangePickerState) {
            showDialog = !showDialog
            start = DateUtils.convertMillisToYymmdd(dateRangePickerState.selectedStartDateMillis!!)
            end = DateUtils.convertMillisToYymmdd(dateRangePickerState.selectedEndDateMillis!!)
        }
    }

    Scaffold(
        topBar = {
            TopContentWrite(
                title = "캠페인 등록",
                navController = navController,
                completeButtonState =
                titleTextState.isNotBlank() && selectedOcean != null,
                onCompleteClick = {
                    mapViewModel.setLoading()
                    val link = communityViewModel.link.value
                    mapViewModel.postCampaign(Campaign(
                        authority = contentTextState,
                        completionStatus = false,
                        startDate = start,
                        endDate = end,
                        locationId = selectedOcean!!.id,
                        name = titleTextState,
                        url = link.url,
                    ))
                }
            )
        },
    ) { innerPaddings ->
        Surface(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPaddings)
        ) {
            if(isLoading) {
                LoadingView()
            }
            Column(
                modifier = modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .pointerInput(Unit) {
                        detectTapGestures {
                            keyboardController?.hide()
                        }
                    }
            ) {
                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 32.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = titleTextState,
                        onValueChange = { titleTextState = it },
                        textStyle = TextStyle(
                            fontSize = 20.sp,
                            lineHeight = 18.sp,
                            fontWeight = FontWeight(700),
                            color = Black_100,
                        ),
                        singleLine = true,
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Next),
                        decorationBox = { innerTextField ->
                            if (titleTextState.isEmpty()) {
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

                    BasicTextField(
                        modifier = modifier.fillMaxWidth(),
                        value = contentTextState,
                        onValueChange = { contentTextState = it },
                        textStyle = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight(400),
                            color = Black_100,
                        ),
                        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
                        decorationBox = { innerTextField ->
                            if (contentTextState.isEmpty()) {
                                Text(
                                    text = "주최 추가",
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

                    Row(verticalAlignment = Alignment.CenterVertically) {
                        ElevatedFilterChip(selected = false, onClick = { showDialog = true }, label = { Text(
                            text = "기간"
                        ) })
                        Spacer(modifier = Modifier.size(8.dp))
                        Text(text = start)
                        Text(text = " ~ ")
                        Text(text = end)
                    }
                    ReportDropDown(locations, mapViewModel = mapViewModel, hint = "캠페인을 진행하는 해변을 선택해주세요.")
                    LinkView(hint = "캠페인 참여 링크를 추가해주세요.")
                }
            }
        }
    }
}


@Composable
@OptIn(ExperimentalMaterial3Api::class)
private fun SimpleDateRangePickerInDatePickerDialog(
    openDialog: Boolean,
    dateRangePickerState: DateRangePickerState,
    onDismiss: () -> Unit
) {
    Column(
        modifier = Modifier.padding(vertical = 10.dp, horizontal = 0.dp),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.End
    ) {
        DatePickerDialog(
            shape = RoundedCornerShape(6.dp),
            onDismissRequest = onDismiss,
            confirmButton = {
                // Seems broken at the moment with DateRangePicker
                // Works fine with DatePicker
            },
        ) {

            DateRangePicker(
                modifier = Modifier.weight(1f), // Important to display the button
                state = dateRangePickerState,
                title = { },
                headline = {}
            )

            TextButton(
                onClick = {
                    onDismiss()
                },
                modifier = Modifier
                    .padding(12.dp)
                    .align(Alignment.End),
                enabled = dateRangePickerState.selectedEndDateMillis != null
            ) {
                Text(text = "확인")
            }
        }
    }
}