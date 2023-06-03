package com.example.worldskillsapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskillsapp.data.local.preferencesDataStore.IDataStore
import com.example.worldskillsapp.domain.models.MaleModel
import com.example.worldskillsapp.domain.validators.CredentialValidator
import com.example.worldskillsapp.domain.validators.DateValidator
import com.example.worldskillsapp.domain.validators.ValidateResult
import com.example.worldskillsapp.presentation.events.InputUserDataEvents
import com.example.worldskillsapp.presentation.state.UserDataState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class InputUserDataViewModel(
    private val dataStore: IDataStore,
    private val dateValidator: DateValidator,
    private val credentialValidator: CredentialValidator
) : ViewModel() {
    private val _viewState = MutableStateFlow(UserDataState())
    val viewState = _viewState.asStateFlow()

    fun onEvent(event: InputUserDataEvents) {
        when (event) {
            is InputUserDataEvents.OnBirthdayChange -> onBirthdayChange(event.birthday)
            is InputUserDataEvents.OnMaleChange -> onMaleChange(event.male)
            is InputUserDataEvents.OnNameChange -> onNameChange(event.name)
            is InputUserDataEvents.OnPasswordChange -> onPasswordChange(event.password, event.successCallback)
            is InputUserDataEvents.OnPatronymicChange -> onPatronymicChange(event.patronymic)
            is InputUserDataEvents.OnSurnameChange -> onSurnameChange(event.surname)
            is InputUserDataEvents.OnClickConfirmButton -> createUser(event.successCallback)
            InputUserDataEvents.OnDelPasswordChange -> onDelPasswordChange()
        }
    }

    private fun createUser(onSuccessCallback: () -> Unit) {
        viewModelScope.launch {
            _viewState.update { state -> state.copy(isLoading = true) }
            delay(1000)
            _viewState.update { state -> state.copy(isLoading = false) }
            onSuccessCallback()
        }
    }

    private fun onNameChange(name: String) {
        viewModelScope.launch {
            _viewState.update { state ->
                state.copy(
                    name = name,
                    nameValidateResult = credentialValidator(name)
                )
            }
        }
    }

    private fun onSurnameChange(surname: String) {
        viewModelScope.launch {
            _viewState.update { state ->
                state.copy(
                    surname = surname,
                    surnameValidateResult = credentialValidator(surname)
                )
            }
        }
    }

    private fun onPatronymicChange(patronymic: String) {
        viewModelScope.launch {
            _viewState.update { state ->
                state.copy(
                    patronymic = patronymic,
                    patronymicValidateResult = credentialValidator(patronymic)
                )
            }
        }
    }

    private fun onBirthdayChange(birthday: String) {
        viewModelScope.launch {
            _viewState.update { state ->
                state.copy(
                    birthday = birthday,
                    dateValidateResult = ValidateResult(successful = true)
                )
            }
        }
    }

    private fun onPasswordChange(password: String, onSuccessCallback: () -> Unit) {
        viewModelScope.launch {
            if (viewState.value.password.length < 4) {
                _viewState.update { state ->
                    state.copy(password = state.password + password)
                }
                if (viewState.value.password.length == 4) {
                    _viewState.update { state -> state.copy(isLoading = true) }
                    delay(1500)

                    onSuccessCallback()
                    _viewState.update { state -> state.copy(isLoading = false) }
                }
            }
        }
    }

    private fun onDelPasswordChange() {
        viewModelScope.launch {
            _viewState.update { state ->
                state.copy(password = state.password.dropLast(1))
            }
        }
    }

    private fun onMaleChange(male: MaleModel) {
        viewModelScope.launch {
            _viewState.update { state ->
                state.copy(male = male)
            }
        }
    }

}