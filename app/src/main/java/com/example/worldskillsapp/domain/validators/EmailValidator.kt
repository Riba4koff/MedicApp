package com.example.worldskillsapp.domain.validators

class EmailValidator : Validator<String> {
    override fun invoke(form: String): ValidateResult {
        return if (form.isEmpty()) ValidateResult(message = "E-mail не должен быть пустым")
        else if (emailRegex.matches(form) && form.trim()
                .contains(form.lowercase().trim())
        ) ValidateResult(successful = true)
        else ValidateResult("Неправильный тип E-mail")
    }

    companion object {
        private val emailRegex = ("[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+").toRegex()
    }
}