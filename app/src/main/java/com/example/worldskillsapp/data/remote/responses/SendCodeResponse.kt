package com.example.worldskillsapp.data.remote.responses

import kotlinx.serialization.Serializable

@Serializable
data class SendCodeResponse(
    val code: String
)
