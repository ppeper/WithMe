package com.bonobono.presentation.ui.component

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.bonobono.presentation.R
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.TextGray

@Composable
fun BasicTextField(
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            textColor = TextGray,
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = LightGray
        )
    )
}

@Composable
fun TextFieldWithButton(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes buttonTxt: Int,
    action: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp)
                .weight(2f),
            value = value,
            onValueChange = onValueChange,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                textColor = TextGray,
                focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = LightGray
            )
        )

        Button(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = PrimaryBlue
            ),
            onClick = { action }) {
            BasicButtonText(text = stringResource(buttonTxt))
        }
    }
}

@Composable
fun ProfileEditTextField(
    value: String,
    onValueChange: (String) -> Unit,
    readOnly : Boolean
) {
    TextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        value = value,
        readOnly = readOnly,
        onValueChange = onValueChange)
}

@Preview(showBackground = true)
@Composable
fun textFieldPreview() {
    val onChange: (String) -> Unit
    TextFieldWithButton(value = "test", onValueChange = {}, buttonTxt = R.string.community_alert_title) {
    }
}