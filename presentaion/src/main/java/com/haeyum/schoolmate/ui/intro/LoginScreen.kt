/*
 * Created by PangMoo on 2022/12/8
 */

package com.haeyum.schoolmate.ui.intro

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.haeyum.schoolmate.ui.theme.SchoolmateTheme
import com.haeyum.schoolmate.ui.theme.TextColor

@Composable
fun LoginScreen(onLoginSuccess: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = if (isSystemInDarkTheme()) MaterialTheme.colors.background else MaterialTheme.colors.secondaryVariant)
    ) {
        var showTermsAlert by remember { mutableStateOf(false) }

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
                onClick = onLoginSuccess,
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
                text = buildAnnotatedString {
                    append("로그인 시 ")
                    withStyle(
                        style = SpanStyle(
                            color = MaterialTheme.colors.primary,
                            fontWeight = FontWeight.Normal
                        )
                    ) {
                        append("이용약관")
                    }
                    append(" 동의로 간주합니다.")
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 12.dp)
                    .clickable {
                        showTermsAlert = true
                    },
                color = MaterialTheme.colors.secondaryVariant,
                fontSize = 10.sp,
                fontWeight = FontWeight.ExtraLight,
                textAlign = TextAlign.Center
            )
            Text(
                text = "사용되는 학번/비밀번호는 E-Class 계정 입니다.\n로그인 시 AES, RSA로 이중 암호화되기에 안전합니다.",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                color = Color(0xFFB8B8D2),
                fontSize = 12.sp,
                fontWeight = FontWeight.Light,
                textAlign = TextAlign.Center
            )
        }

        AnimatedVisibility(visible = showTermsAlert) {
            AlertDialog(
                onDismissRequest = { showTermsAlert = false },
                buttons = {
                    TextButton(
                        onClick = { showTermsAlert = false },
                        modifier = Modifier
                            .align(Alignment.End)
                            .padding(end = 20.dp, bottom = 20.dp),
                        contentPadding = PaddingValues(4.dp),
                    ) {
                        Text(
                            text = "확인",
                            color = MaterialTheme.colors.primary,
                            fontSize = 16.sp
                        )
                    }
                },
                title = { Text(text = "이용약관") },
                text = {
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .verticalScroll(rememberScrollState())
                    ) {
                        Text(
                            text = "1. 스쿨메이트 앱을 사용하기 위해 다음의 약관을 동의하셔야만 합니다.\n" +
                                    "2. 본 앱은 GDSC TUK에서 진행된 토이 프로젝트로 언제든지 서비스가 중단 및 데이터를 초기화할 수 있습니다.\n" +
                                    "3. 로그인 시 tukorea.ac.kr, eclass.tukorea.ac.kr 사이트를 통해 이용자의 정보를 취득할 수 있습니다.\n" +
                                    "4. 이때 사용자의 이름, 학번, 성별, 수강 과목, 과제, 일정, 알림 정보를 수집하며, 프로필 사진 이용을 위해 이용자의 증명사진을 저장할 수 있습니다.\n" +
                                    "5. 이용자는 언제든지 관리자에게 계정 삭제를 요청할 수 있으며, 관리자는 요청 확인 즉시 해당 정보를 복구가 불가능하도록 파기합니다.\n" +
                                    "6. 이용자는 게시판 이용 시 정치적 발언, 비난, 욕설 등 사회적으로 문제를 발생하는 내용의 글을 작성해서는 안되며, 적발 시 삭제 및 법적 문제가 발생하여도 스쿨메이트 측은 책임을 지지 않습니다.\n" +
                                    "7. 스쿨메이트 앱을 사용하면서 발생하는 문제는 모두 이용자 측이 감수해야합니다."
                        )
                    }
                }
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
        LoginScreen(onLoginSuccess = {})
    }
}