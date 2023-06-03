package com.example.worldskillsapp.data.local.preferencesDataStore

@kotlinx.serialization.Serializable
data class Settings(
    val token: String = "",
    val onBoardInfo: Boolean = false,
    val password: String = "",
    val signInCode: String = ""
)