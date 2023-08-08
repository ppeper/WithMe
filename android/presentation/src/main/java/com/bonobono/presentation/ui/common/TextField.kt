package com.bonobono.presentation.ui.common

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bonobono.presentation.ui.common.text.CustomTextStyle
import com.bonobono.presentation.ui.common.text.CustomTextStyle.primaryColorBtnText
import com.bonobono.presentation.ui.theme.Black_100
import com.bonobono.presentation.ui.theme.DarkGray
import com.bonobono.presentation.ui.theme.Green
import com.bonobono.presentation.ui.theme.LightGray
import com.bonobono.presentation.ui.theme.PrimaryBlue
import com.bonobono.presentation.ui.theme.Red
import com.bonobono.presentation.ui.theme.TextGray

@Composable
fun CommonTextField(
    text: String,
    textStyle: TextStyle,
    singleLine: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
    hint: String,
    onValueChange: (String) -> Unit
) {
    BasicTextField(
        modifier = Modifier.fillMaxWidth(),
        value = text,
        onValueChange = onValueChange,
        textStyle = textStyle,
        singleLine = singleLine,
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            if (text.isEmpty()) {
                Text(
                    text = hint,
                    style = textStyle.copy(color = TextGray)
                )
            }
            innerTextField()
        }
    )
}

@Composable
fun BasicTextField(
    value: String,
    hint: String,
    keyboardType: KeyboardType,
    onValueChange: (String) -> Unit
) {
    val visualTransformation = if (keyboardType == KeyboardType.Password) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Black_100,
            unfocusedTextColor = TextGray,
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = LightGray
        ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = TextGray
        ),
        placeholder = { Text(text = hint,
            style = TextStyle(
                color = TextGray
            )
        ) },
        singleLine = true,
        shape = RoundedCornerShape(6.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        visualTransformation = visualTransformation
    )
}

@Composable
fun PasswordTextField(
    value: String,
    hint: String,
    keyboardType: KeyboardType,
    supportingText : String,
    onValueChange: (String) -> Unit
) {
    val visualTransformation = if (keyboardType == KeyboardType.Password) {
        PasswordVisualTransformation()
    } else {
        VisualTransformation.None
    }
    OutlinedTextField(
        modifier = Modifier
            .fillMaxWidth(),
        value = value,
        onValueChange = onValueChange,
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Black_100,
            unfocusedTextColor = TextGray,
            focusedBorderColor = PrimaryBlue,
            unfocusedBorderColor = LightGray
        ),
        textStyle = TextStyle(
            fontSize = 14.sp,
            color = TextGray
        ),
        placeholder = { Text(text = hint,
            style = TextStyle(
                color = TextGray
            )
        ) },
        singleLine = true,
        shape = RoundedCornerShape(6.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        supportingText = { Text(text = supportingText,
            style = TextStyle(
                fontSize = 10.sp,
                color = if(supportingText == "일치하지 않습니다") Red else Green
            )
        )},
        visualTransformation = visualTransformation
    )
}

@Composable
fun TextFieldWithButton(
    value: String,
    onValueChange: (String) -> Unit,
    @StringRes buttonTxt: Int,
    hint: String,
    keyboardType : KeyboardType,
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
            placeholder = { Text(text = hint,
                style = TextStyle(
                    color = TextGray
                )
            ) },
            keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
            shape = RoundedCornerShape(6.dp),
            onValueChange = onValueChange,
            colors = OutlinedTextFieldDefaults.colors(
                focusedTextColor = TextGray,
                unfocusedTextColor = DarkGray,
                focusedBorderColor = PrimaryBlue,
                unfocusedBorderColor = LightGray
            ),
            textStyle = TextStyle(
                fontSize = 14.sp,
                color = TextGray
            ),
        )

        Button(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue
            ),
            shape = RoundedCornerShape(6.dp),
            onClick = { action }) {
            Text(
                text = stringResource(buttonTxt),
                style = primaryColorBtnText,
                modifier = Modifier.padding(vertical = 8.dp)
            )
        }
    }
}

@Composable
fun GameTextFieldWithButton(
    value: String,
    onValueChange: (String) -> Unit,
    buttonTxt: String,
    action: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(0.dp, 0.dp, 16.dp, 0.dp)
                .weight(3f),
            value = value,
            onValueChange = onValueChange,
            textStyle = CustomTextStyle.quizContentStyle
        )
        Button(modifier = Modifier
            .fillMaxWidth()
            .weight(1f),
            colors = ButtonDefaults.buttonColors(
                containerColor = PrimaryBlue
            ),
            onClick = { action }) {
            Text(text = "확인", style = CustomTextStyle.quizContentStyle)
        }
    }
}