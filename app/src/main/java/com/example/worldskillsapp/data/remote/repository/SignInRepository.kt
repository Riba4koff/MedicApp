package com.example.worldskillsapp.data.remote.repository

import com.example.worldskillsapp.data.remote.IApi
import com.example.worldskillsapp.data.remote.responses.SendCodeResponse
import com.example.worldskillsapp.domain.repository.ISignInRepository

class SignInRepository(
    private val api: IApi
): ISignInRepository {
    override suspend fun sendCode(email: String): SendCodeResponse? {
        val response = api.sendCode(email)
        return if (response.isSuccess) response.getOrNull()
        else null
    }
}