package com.example.worldskillsapp.domain.validators

interface Validator<in Input> {
    operator fun invoke(form: Input): ValidateResult
}