package com.example.worldskillsapp.presentation.state

import com.example.worldskillsapp.domain.models.MaleModel
import com.example.worldskillsapp.domain.models.MaleModel.*
import com.example.worldskillsapp.domain.validators.ValidateResult

data class UserDataState(
    val password: String = "",
    val name: String = "",
    val surname: String = "",
    val patronymic: String = "",
    val male: MaleModel = Unselected(),
    val listMales: List<MaleModel> = listOf(Man(), Women(), Unselected()),
    val birthday: String = "",
    val isLoading: Boolean = false,
    val confirmButtonIsEnabled: Boolean = false,

    val dateValidateResult: ValidateResult = ValidateResult(),
    val nameValidateResult: ValidateResult = ValidateResult(),
    val surnameValidateResult:ValidateResult = ValidateResult(),
    val patronymicValidateResult: ValidateResult = ValidateResult()
)
