package com.example.worldskillsapp.presentation.state

import com.example.worldskillsapp.domain.validators.ValidateResult

data class SignInState(
    val email: String = "",
    val code: String = "",
    val isLoading: Boolean = false,
    val timer: Int = 60,
    val validateEmail: ValidateResult = ValidateResult()
)
