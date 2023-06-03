package com.example.worldskillsapp.data.remote

import com.example.worldskillsapp.data.remote.responses.SendCodeResponse
import io.ktor.client.statement.HttpResponse

interface IApi {
    suspend fun sendCode(email: String): Result<SendCodeResponse>

    companion object {
        private const val route = "https://medic.madskill.ru"
    }

    sealed class Endpoints(val url: String) {
        object SendCode: Endpoints("$route/api/sendCode")
    }
}