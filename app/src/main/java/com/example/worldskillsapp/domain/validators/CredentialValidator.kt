package com.example.worldskillsapp.domain.validators

class CredentialValidator: Validator<String> {
    override fun invoke(form: String): ValidateResult {
        return if (form.isEmpty()) ValidateResult(message = "Поле не может быть пустым", successful = false)
        else if (form.length < 2) ValidateResult(message = "Длина не может быть меньше 2")
        else ValidateResult(successful = true)
    }
}