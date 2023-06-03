@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.worldskillsapp.presentation.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldskillsapp.R
import com.example.worldskillsapp.domain.validators.ValidateResult
import com.example.worldskillsapp.presentation.events.SignInEvents
import com.example.worldskillsapp.presentation.state.SignInState
import com.example.worldskillsapp.ui.theme.ConfirmButtonColor
import com.example.worldskillsapp.ui.theme.WorldSkillsAppTheme
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun SignIn(
    state: SignInState,
    onEvent: (SignInEvents) -> Unit,
    onClickConfirm: () -> Unit,
) {
    SignInContent(
        email = state.email,
        onEmailChange = { onEvent(SignInEvents.OnEmailChange(it)) },
        onClickConfirm = {
            onEvent(SignInEvents.SendEmail)
            onClickConfirm()
        },
        isErrorEmail = state.validateEmail
    )
}

@Composable
fun SignInContent(
    email: String,
    onEmailChange: (String) -> Unit,
    onClickConfirm: () -> Unit,
    isErrorEmail: ValidateResult,
) {
    Box(modifier = Modifier.fillMaxSize()) {
        TopContentSignIn(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 32.dp, start = 16.dp),
            email = email,
            onEmailChange = onEmailChange,
            onClickConfirm = onClickConfirm,
            isErrorEmail = isErrorEmail
        )
        SignInWithYandexButton(modifier = Modifier.align(Alignment.BottomCenter)) {

        }
    }
}

@Composable
fun TopContentSignIn(
    modifier: Modifier = Modifier,
    email: String,
    onEmailChange: (String) -> Unit,
    onClickConfirm: () -> Unit,
    isErrorEmail: ValidateResult,
) {
    Column {
        Row(modifier = modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = R.drawable.hand_icon),
                contentDescription = null
            )
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Добро пожаловать",
                fontSize = 24.sp,
                fontWeight = FontWeight.W700,
                color = Color.Black
            )
        }
        Text(
            modifier = Modifier.padding(start = 16.dp, top = 16.dp),
            text = "Войдите, чтобы пользоваться функциями приложения",
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Black
        )
        Spacer(modifier = Modifier.height(64.dp))
        Text(
            modifier = Modifier.padding(start = 16.dp),
            text = "Вход по E-mail",
            fontSize = 14.sp,
            fontWeight = FontWeight.W400,
            color = Color.Gray
        )
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 4.dp),
            value = email,
            onValueChange = onEmailChange,
            isError = isErrorEmail,
            placeholder = "example@email.com",
            trailingIcon = R.drawable.email_icon
        )
        CustomButton(
            modifier = Modifier
                .fillMaxWidth()
                .height(80.dp)
                .padding(16.dp, 16.dp),
            title = "Подтвердить",
            onClick = onClickConfirm,
            enabled = email.isNotEmpty() && isErrorEmail.successful
        )
    }
}

@Composable
fun SignInWithYandexButton(
    modifier: Modifier = Modifier,
    onClickSignInWithYandex: () -> Unit,
) {
    Column(
        modifier = modifier.padding(bottom = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Или войдите с помощью",
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = Color.Gray
        )
        Spacer(modifier = Modifier.height(8.dp))
        OutlinedButton(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp, 0.dp)
                .height(56.dp),
            shape = RoundedCornerShape(8.dp),
            onClick = onClickSignInWithYandex
        ) {
            Text(
                text = "Войти с Яндекс",
                fontSize = 17.sp,
                fontWeight = FontWeight.W500,
                color = Color.Black
            )
        }
    }
}

@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    isError: ValidateResult,
    placeholder: String,
    trailingIcon: Int? = null,
    readOnly: Boolean = false,
    onClickTrailingIcon: (() -> Unit)? = null
) {
    val focus = LocalFocusManager.current
    TextField(
        modifier = modifier,
        value = value,
        onValueChange = onValueChange,
        shape = RoundedCornerShape(10.dp),
        colors = TextFieldDefaults.textFieldColors(
            disabledIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedLabelColor = Color.Transparent,
            errorIndicatorColor = Color.Transparent,
            errorLabelColor = Color.Gray,
            errorTrailingIconColor = Color.Gray,
            disabledTrailingIconColor = Color.Gray,
            focusedTrailingIconColor = Color.Gray,
            unfocusedLabelColor = Color.Gray
        ),
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray
            )
        },
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
        keyboardActions = KeyboardActions(onDone = {
            focus.clearFocus()
        }),
        isError = isError.successful.not(),
        trailingIcon = {
            if (trailingIcon != null && onClickTrailingIcon != null) {
                IconButton(onClick = onClickTrailingIcon) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = trailingIcon),
                        contentDescription = null
                    )
                }
            }
            else if (trailingIcon != null){
                Icon(
                    imageVector = ImageVector.vectorResource(id = trailingIcon),
                    contentDescription = null
                )
            }
        },
        readOnly = readOnly
    )
}

@Composable
fun CustomButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit,
    enabled: Boolean,
) {
    Button(
        modifier = modifier, onClick = onClick, colors = ButtonDefaults.buttonColors(
            contentColor = Color.White,
            containerColor = ConfirmButtonColor,
        ),
        enabled = enabled,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            text = title,
            fontSize = 17.sp,
            fontWeight = FontWeight.W600
        )
    }
}

@Preview
@Composable
fun SignInPreview() {
    WorldSkillsAppTheme {
        Surface {
            SignIn(state = SignInState(), onEvent = {

            }, onClickConfirm = {

            })
        }
    }
}