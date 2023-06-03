package com.example.worldskillsapp.domain.validators

class DateValidator : Validator<String> {
    override fun invoke(form: String): ValidateResult {
        return if (regex.matches(form)) ValidateResult(successful = true)
        else if (form.isBlank()) ValidateResult()
        else ValidateResult()

    }

    companion object {
        val regex = ("(0?[1-9]|[12][0-9]|3[01])\\.(0?[1-9]|1[012])" + "\\.((19|20)\\d\\d)").toRegex()
    }
}