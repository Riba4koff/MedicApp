package com.example.worldskillsapp.presentation.events

import com.example.worldskillsapp.domain.models.MaleModel

sealed interface InputUserDataEvents {
    data class OnPasswordChange(val password: String, val successCallback: () -> Unit): InputUserDataEvents
    data class OnNameChange(val name: String): InputUserDataEvents
    data class OnSurnameChange(val surname: String): InputUserDataEvents
    data class OnPatronymicChange(val patronymic: String): InputUserDataEvents
    data class OnBirthdayChange(val birthday: String): InputUserDataEvents
    data class OnMaleChange(val male: MaleModel): InputUserDataEvents

    object OnDelPasswordChange: InputUserDataEvents
    data class OnClickConfirmButton(val successCallback: () -> Unit): InputUserDataEvents
}