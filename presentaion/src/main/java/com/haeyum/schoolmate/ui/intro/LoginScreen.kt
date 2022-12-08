/*
 * Created by PangMoo on 2022/12/8
 */

package com.haeyum.schoolmate.ui.intro

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

@Composable
fun LoginScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (isSystemInDarkTheme()) MaterialTheme.colors.background else MaterialTheme.colors.secondaryVariant)
    ) {
        val (studentId, setStudentId) = remember {
            mutableStateOf("")
        }
        val (password, setPassword) = remember {
            mutableStateOf("")
        }

        Text(
            text = "Login",
            modifier = Modifier
                .padding(top = 44.dp, bottom = 30.dp)
                .padding(horizontal = 30.dp),
            color = TextColor,
            fontSize = 25.sp,
            fontWeight = FontWeight.Bold
        )

        Column(
            modifier = Modifier
                .weight(1f)
                .background(color = MaterialTheme.colors.surface)
                .padding(30.dp)
        ) {
            AccountTextField(
                label = "학번",
                value = studentId,
                onValueChange = setStudentId,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Decimal,
                    imeAction = ImeAction.Next
                ),
                keyboardActions = KeyboardActions(
                    onNext = {

                    }
                )
            )
            AccountTextField(
                label = "비밀번호",
                value = password,
                onValueChange = setPassword,
                modifier = Modifier.padding(top = 15.dp),
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password,
                    imeAction = ImeAction.Done
                ),
                keyboardActions = KeyboardActions(
                    onNext = {

                    }
                ),
                visualTransformation = PasswordVisualTransformation()
            )

            Button(
                onClick = { /*TODO*/ },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                shape = RoundedCornerShape(8.dp),
                colors = ButtonDefaults.buttonColors(contentColor = MaterialTheme.colors.secondary),
                contentPadding = PaddingValues(17.dp)
            ) {
                Text(
                    text = "로그인",
                    color = Color.White,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
            Text(
                text = "사용되는 학번/비밀번호는 E-Class 계정 입니다.\n로그인 시 AES, RSA로 이중 암호화되기에 안전합니다.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 30.dp),
                color = Color(0xFFB8B8D2),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        }
    }
}

@Composable
private fun AccountTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    keyboardActions: KeyboardActions = KeyboardActions.Default,
    visualTransformation: VisualTransformation = VisualTransformation.None
) {
    Column(modifier = modifier) {
        Text(
            text = label,
            color = if (isSystemInDarkTheme()) MaterialTheme.colors.secondaryVariant else Color.Black,
            fontSize = 16.sp,
            fontWeight = FontWeight.Light
        )
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 5.dp)
                .then(
                    if (isSystemInDarkTheme())
                        Modifier.background(
                            color = Color(0xFF3E3E56),
                            shape = RoundedCornerShape(6.dp)
                        )
                    else
                        Modifier.border(
                            width = 1.dp,
                            color = MaterialTheme.colors.secondaryVariant,
                            shape = RoundedCornerShape(6.dp)
                        )
                )
                .padding(10.dp),
            textStyle = TextStyle(
                color = if (isSystemInDarkTheme()) Color(0xFFB8B8D2) else Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Light
            ),
            keyboardOptions = keyboardOptions,
            keyboardActions = keyboardActions,
            singleLine = true,
            visualTransformation = visualTransformation
        )
    }
}

@Preview
@Composable
fun LoginScreenPreview() {
    SchoolmateTheme {
        LoginScreen()
    }
}