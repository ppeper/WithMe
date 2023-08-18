package com.bonobono.presentation.ui.community.views.board

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.bonobono.domain.model.map.Location
import com.bonobono.presentation.ui.community.util.DummyData
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.TextGray
import com.bonobono.presentation.ui.theme.White
import com.bonobono.presentation.viewmodel.MapViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ReportDropDown(
    locations: List<Location>,
    mapViewModel: MapViewModel,
    hint: String = "신고할 해변을 선택해주세요"
) {
    var expandedState by remember { mutableStateOf(false) }
    var selectedOption = mapViewModel.selectedLocation.collectAsStateWithLifecycle()
    Log.d("TEST", "ReportDropDown 메뉴: $locations")
    ExposedDropdownMenuBox(
        expanded = expandedState,
        onExpandedChange = { expandedState = !expandedState }
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = selectedOption.value?.name ?: hint,
            onValueChange = {},
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = Black_100,
                unfocusedTextColor = TextGray,
                focusedBorderColor = DarkGray,
                unfocusedBorderColor = LightGray
            ),
            placeholder = { Text(text = "신고할 해변을 선택해주세요",
                style = TextStyle(
                    color = TextGray
                )
            ) },
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = Black_100
            ),
            readOnly = true,
            shape = RoundedCornerShape(6.dp),
            trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expandedState) }
        )
        ExposedDropdownMenu(
            modifier = Modifier
                .fillMaxWidth()
                .background(White),
            expanded = expandedState,
            onDismissRequest = { expandedState = false }
        ) {
            locations.forEach { location ->
                DropdownMenuItem(
                    modifier = Modifier
                        .fillMaxWidth(),
                    onClick = {
                        expandedState = false
                        mapViewModel.setSelectedLocation(location)
                    },
                    text = { Text(text = location.name) }
                )
            }
        }
    }
}

@Preview
@Composable
fun PreviewReportDropDown() {
    ReportDropDown(locations = DummyData.locations, mapViewModel = hiltViewModel())
}