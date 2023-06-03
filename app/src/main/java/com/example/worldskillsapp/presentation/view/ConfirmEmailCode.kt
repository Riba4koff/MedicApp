@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.worldskillsapp.presentation.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldskillsapp.R
import com.example.worldskillsapp.presentation.events.SignInEvents
import com.example.worldskillsapp.presentation.state.SignInState
import com.example.worldskillsapp.ui.theme.WorldSkillsAppTheme
import com.ramcosta.composedestinations.annotation.Destination
import kotlinx.coroutines.delay

@Destination
@Composable
fun ConfirmEmailCode(
    popBackStack: () -> Unit,
    state: SignInState,
    onEvent: (SignInEvents) -> Unit,
    onSuccessCallback: () -> Unit
) {
    ConfirmEmailCodeContent(
        popBackStack = popBackStack,
        timer = state.timer,
        code = state.code,
        onCodeChange = { onEvent(SignInEvents.OnCodeChange(it)) },
        sendCode = { code ->
            onEvent(SignInEvents.SendCode(code, onSuccessCallback = onSuccessCallback))
        },
        loading = state.isLoading,
        sendCodeAgain = { onEvent(SignInEvents.SendCodeAgain) }
    )
}

@Composable
fun ConfirmEmailCodeContent(
    popBackStack: () -> Unit,
    timer: Int,
    code: String,
    onCodeChange: (String) -> Unit,
    sendCode: (String) -> Unit,
    loading: Boolean,
    sendCodeAgain: () -> Unit
) {
    Box(Modifier.fillMaxSize()) {
        PopBackStackButton {
            popBackStack()
        }
        Column(
            Modifier
                .fillMaxWidth()
                .height(300.dp)
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleEmailCode(title = "Введите код из E-mail")

            Spacer(modifier = Modifier.height(16.dp))

            CodeRowTextField(
                code = code,
                onCodeChange = onCodeChange,
                sendCode = sendCode,
                loading = loading
            )
            Spacer(modifier = Modifier.height(16.dp))

            CustomButton(
                title = "Отправить код повторно", modifier = Modifier
                    .fillMaxWidth()
                    .padding(64.dp, 0.dp), onClick = sendCodeAgain, enabled = timer == 0
            )

            Spacer(modifier = Modifier.height(16.dp))

            TimerText(text = "Отправить код повторно можно")
            TimerText(text = "будет через ${timer} секунд")

            Spacer(modifier = Modifier.height(16.dp))

            LoadingIndicator(visible = loading)
        }
    }
}

@Composable
fun LoadingIndicator(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(),
        exit = fadeOut()
    ) {
        CircularProgressIndicator()
    }
}

@Composable
fun PopBackStackButton(popBackStack: () -> Unit) {
    IconButton(modifier = Modifier.padding(8.dp), onClick = popBackStack) {
        Image(
            modifier = Modifier.size(32.dp),
            painter = painterResource(id = R.drawable.back_stack_button),
            contentDescription = null
        )
    }
}

@Composable
fun TitleEmailCode(title: String) {
    Text(
        text = title, fontSize = 17.sp, fontWeight = FontWeight.W600, color = Color.Black
    )
}


@Composable
fun CodeRowTextField(
    code: String,
    onCodeChange: (String) -> Unit,
    sendCode: (String) -> Unit,
    loading: Boolean,
) {
    val focus = LocalFocusManager.current
    BasicTextField(
        value = code,
        onValueChange = {
            if (it.length <= 4) onCodeChange(it)
            if (it.length == 4) {
                sendCode(code)
                focus.clearFocus()
            }
        },
        enabled = !loading,
        decorationBox = {
            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(64.dp, 0.dp), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                repeat(4) { index ->
                    BoxCode(
                        char = try {
                            code[index]
                        } catch (e: Exception) {
                            ' '
                        }
                    )
                }
            }
        },
        keyboardActions = KeyboardActions(onDone = {
            focus.clearFocus()
        }),
        keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done)
    )
}

@Composable
fun BoxCode(
    char: Char
) {
    Card(modifier = Modifier.size(50.dp)) {
        Box(modifier = Modifier.size(50.dp), contentAlignment = Alignment.Center) {
            Text(
                text = "$char",
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
            )
        }
    }
}

@Composable
fun TimerText(
    text: String,
) {
    Text(
        modifier = Modifier.padding(64.dp, 0.dp),
        text = text,
        fontSize = 15.sp,
        fontWeight = FontWeight.W400,
        color = Color.Gray
    )
}

@Preview
@Composable
fun ConfirmEmailCodePreview() {
    WorldSkillsAppTheme {
        Surface {
            ConfirmEmailCode(
                state = SignInState(),
                popBackStack = {

                },
                onEvent = {

                },
                onSuccessCallback = {

                }
            )
        }
    }
}