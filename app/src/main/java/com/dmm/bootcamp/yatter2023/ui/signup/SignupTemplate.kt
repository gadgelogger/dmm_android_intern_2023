package com.dmm.bootcamp.yatter2023.ui.login

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dmm.bootcamp.yatter2023.ui.theme.Yatter2023Theme

@Composable
fun SignupTemplate(
    userName: String,
    onChangedUserName: (String) -> Unit,
    password: String,
    onChangedPassword: (String) -> Unit,
    isEnableLogin: Boolean,
    isLoading: Boolean,
    onClickLogin: () -> Unit,
    onClickRegister: () -> Unit,
) {
    Scaffold(
    ) { Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(it)
            .padding(8.dp),
    ) {
        Column(modifier = Modifier.fillMaxSize()) {

            // 追加: 大きなタイポグラフィ
            Column(
                modifier = Modifier
                    .padding(top = 110.dp, start = 15.dp)  // 調整して適切な位置に配置
            ) {
                Text(
                    text = "Signup",
                    style = MaterialTheme.typography.h3,  // お好みのスタイルに調整
                    fontWeight = FontWeight.Bold,
                    fontSize = 80.sp
                )
            }


            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp, top = 100.dp),
                value = userName,
                onValueChange = onChangedUserName,
                placeholder = {
                    Text(text = "ユーザーネーム")
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                )
            )

            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                value = password,
                onValueChange = onChangedPassword,
                placeholder = {
                    Text(text = "パスワード")
                },
                colors = TextFieldDefaults.textFieldColors(
                    backgroundColor = Color.Transparent
                )
            )
            Button(
                enabled = isEnableLogin,
                onClick = onClickLogin,
                modifier = Modifier
                    .fillMaxWidth(),
                shape = RoundedCornerShape(50),

                ) {
                Text(text = "サインアップ")
            }
        }
        if (isLoading) {
            CircularProgressIndicator()
        }
    }
    }
}

@Preview
@Composable
fun SignupTemplatePreview() {
    Yatter2023Theme {
        Surface {
            SignupTemplate(
                userName = "username",
                onChangedUserName = {},
                password = "password",
                onChangedPassword = {},
                isEnableLogin = true,
                isLoading = false,
                onClickLogin = {},
                onClickRegister = {},
            )
        }
    }
}