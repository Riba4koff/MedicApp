package com.example.worldskillsapp.presentation.events

sealed interface SignInEvents {
    data class OnEmailChange(val email: String): SignInEvents
    data class OnCodeChange(val code: String): SignInEvents
    object SendEmail: SignInEvents
    data class SendCode(val code: String, val onSuccessCallback: () -> Unit): SignInEvents
    object SendCodeAgain: SignInEvents
}