@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.worldskillsapp.presentation.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.font.FontWeight.Companion.W400
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.worldskillsapp.R
import com.example.worldskillsapp.domain.models.MaleModel
import com.example.worldskillsapp.domain.validators.ValidateResult
import com.example.worldskillsapp.presentation.events.InputUserDataEvents
import com.example.worldskillsapp.presentation.state.UserDataState
import com.example.worldskillsapp.ui.theme.SkipTextButtonColor
import com.example.worldskillsapp.ui.theme.WorldSkillsAppTheme
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun InputUserData(
    state: UserDataState,
    onEvent: (InputUserDataEvents) -> Unit,
    skipOnClick: () -> Unit,
) {
    InputUserDataContent(
        skipOnClick = skipOnClick,
        name = state.name,
        surname = state.surname,
        patronymic = state.patronymic,
        birthday = state.birthday,
        onNameChange = { onEvent(InputUserDataEvents.OnNameChange(it)) },
        onSurnameChange = { onEvent(InputUserDataEvents.OnSurnameChange(it)) },
        onPatronymicChange = { onEvent(InputUserDataEvents.OnPatronymicChange(it)) },
        onBirthdayChange = { onEvent(InputUserDataEvents.OnBirthdayChange(it)) },
        males = listOf(
            MaleModel.Women(), MaleModel.Man(), MaleModel.Unselected()
        ),
        onSelectedMale = { male ->
            onEvent(InputUserDataEvents.OnMaleChange(male))
        },
        selectedMaleModel = state.male,
        confirmButtonEnabled =
        state.male != MaleModel.Unselected() &&
                state.nameValidateResult.successful &&
                state.surnameValidateResult.successful &&
                state.patronymicValidateResult.successful,
        onClickConfirmButton = {
            onEvent(InputUserDataEvents.OnClickConfirmButton(successCallback = {

            }))
        },
        isLoading = state.isLoading,
        dateValidateResult = state.dateValidateResult,
        nameValidateResult = state.nameValidateResult,
        surnameValidateResult = state.surnameValidateResult,
        patronymicValidateResult = state.patronymicValidateResult
    )
}

@Composable
fun InputUserDataContent(
    skipOnClick: () -> Unit,
    name: String,
    surname: String,
    patronymic: String,
    birthday: String,
    onNameChange: (String) -> Unit,
    onSurnameChange: (String) -> Unit,
    onPatronymicChange: (String) -> Unit,
    onBirthdayChange: (String) -> Unit,
    males: List<MaleModel>,
    onSelectedMale: (MaleModel) -> Unit,
    selectedMaleModel: MaleModel,
    confirmButtonEnabled: Boolean,
    onClickConfirmButton: () -> Unit,
    isLoading: Boolean,
    dateValidateResult: ValidateResult,
    nameValidateResult: ValidateResult,
    surnameValidateResult: ValidateResult,
    patronymicValidateResult: ValidateResult,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        TitleAndSkipText(onClick = skipOnClick)
        DescriptionInputUserData()
        TextFieldsInputUserData(
            name = name,
            surname = surname,
            patronymic = patronymic,
            birthday = birthday,
            onNameChange = onNameChange,
            onSurnameChange = onSurnameChange,
            onPatronymicChange = onPatronymicChange,
            onBirthdayChange = onBirthdayChange,
            dateValidateResult = dateValidateResult,
            nameValidateResult, surnameValidateResult, patronymicValidateResult
        )
        CustomDropDownTextField(
            males = males, onSelectMale = onSelectedMale, selectedMaleModel = selectedMaleModel
        )
        ConfirmButtonUserData(
            onClick = onClickConfirmButton, enabled = confirmButtonEnabled
        )
    }
    LoadingBox(isLoading = isLoading)
}

@Composable
fun LoadingBox(isLoading: Boolean) {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        LoadingIndicator(visible = isLoading)
    }
}

@Composable
fun TitleAndSkipText(onClick: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .padding(top = 16.dp, start = 16.dp, end = 16.dp),
    ) {
        Text(
            modifier = Modifier.align(Alignment.TopStart),
            text = "Создание карты\nпациента",
            fontSize = 24.sp,
            fontWeight = FontWeight.W700,
            color = Color.Black
        )
        Text(
            modifier = Modifier
                .align(Alignment.CenterEnd)
                .padding(bottom = 24.dp)
                .clickable {
                    onClick()
                },
            text = "Пропустить",
            fontSize = 15.sp,
            fontWeight = FontWeight.W400,
            color = SkipTextButtonColor
        )
    }
}

@Composable
fun DescriptionInputUserData() {
    Text(
        modifier = Modifier.padding(
            start = 16.dp, end = 16.dp, top = 16.dp, bottom = 0.dp
        ),
        text = "Без карты пациента вы не сможете заказать анализы.",
        color = Color.Gray,
        fontSize = 13.sp,
        fontWeight = W400
    )
    Text(
        modifier = Modifier.padding(
            start = 16.dp, end = 16.dp, top = 8.dp, bottom = 8.dp
        ),
        text = "В картах пациентов будут храниться результаты анализов вас и ваших близких.",
        color = Color.Gray,
        fontSize = 13.sp,
        fontWeight = W400
    )
}

@Composable
fun TextFieldsInputUserData(
    name: String,
    surname: String,
    patronymic: String,
    birthday: String,
    onNameChange: (String) -> Unit,
    onSurnameChange: (String) -> Unit,
    onPatronymicChange: (String) -> Unit,
    onBirthdayChange: (String) -> Unit,
    dateValidateResult: ValidateResult,
    nameValidateResult: ValidateResult,
    surnameValidateResult: ValidateResult,
    patronymicValidateResult: ValidateResult,
) {
    CustomTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, end = 16.dp, top = 24.dp, bottom = 0.dp
            ),
        value = surname,
        onValueChange = onSurnameChange,
        isError = surnameValidateResult,
        placeholder = "Фамилия"
    )
    CustomTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, end = 16.dp, top = 24.dp, bottom = 0.dp
            ),
        value = name,
        onValueChange = onNameChange,
        isError = nameValidateResult,
        placeholder = "Имя"
    )
    CustomTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, end = 16.dp, top = 24.dp, bottom = 0.dp
            ),
        value = patronymic,
        onValueChange = onPatronymicChange,
        isError = patronymicValidateResult,
        placeholder = "Отчество"
    )
    CustomTextField(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, end = 16.dp, top = 24.dp, bottom = 0.dp
            ),
        value = birthday,
        onValueChange = onBirthdayChange,
        isError = dateValidateResult,
        placeholder = "Дата рождения"
    )
}

@Composable
fun CustomDropDownTextField(
    males: List<MaleModel>,
    onSelectMale: (MaleModel) -> Unit,
    selectedMaleModel: MaleModel,
) {
    val (expanded, onExpandedChange) = remember { mutableStateOf(false) }
    ExposedDropdownMenuBox(
        modifier = Modifier.padding(
            start = 16.dp, end = 16.dp, top = 24.dp, bottom = 0.dp
        ), expanded = expanded, onExpandedChange = onExpandedChange
    ) {
        CustomTextField(
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor(),
            value = selectedMaleModel.value,
            readOnly = true,
            onValueChange = {},
            onClickTrailingIcon = {
                onExpandedChange(expanded.not())
            },
            isError = ValidateResult(),
            placeholder = "Пол",
            trailingIcon = if (expanded) R.drawable.arrow_up_icon else R.drawable.arrow_down_icon
        )
        ExposedDropdownMenu(
            expanded = expanded, onDismissRequest = {
                onExpandedChange(expanded.not())
            }, modifier = Modifier.padding(top = 0.dp, bottom = 0.dp)
        ) {
            males.forEach { male ->
                DropdownMenuItem(modifier = Modifier.fillMaxWidth(), text = {
                    Text(text = male.value)
                }, onClick = {
                    onSelectMale(male)
                    onExpandedChange(expanded.not())
                })
            }
        }
    }
}

@Composable
fun ConfirmButtonUserData(
    onClick: () -> Unit,
    enabled: Boolean,
) {
    CustomButton(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                start = 16.dp, end = 16.dp, top = 48.dp, bottom = 0.dp
            )
            .height(56.dp), title = "Создать", onClick = onClick, enabled = enabled
    )
}

@Preview
@Composable
fun InputUserDataPreview() {
    WorldSkillsAppTheme {
        Surface {
            InputUserData(skipOnClick = {

            }, state = UserDataState(), onEvent = {

            })
        }
    }
}