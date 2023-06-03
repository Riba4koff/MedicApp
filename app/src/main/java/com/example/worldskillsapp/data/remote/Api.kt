package com.example.worldskillsapp.data.remote

import com.example.worldskillsapp.data.remote.responses.SendCodeResponse
import io.ktor.client.request.headers
import io.ktor.client.request.post
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class Api(
    private val ktorHttpClient: KtorHttpClient
): IApi {
    override suspend fun sendCode(email: String): Result<SendCodeResponse> = withContext(Dispatchers.IO) {
        runCatching {
            ktorHttpClient.clientHelper.post(IApi.Endpoints.SendCode.url) {
                headers {
                    append("email", email)
                }
            }
        }
    }
}