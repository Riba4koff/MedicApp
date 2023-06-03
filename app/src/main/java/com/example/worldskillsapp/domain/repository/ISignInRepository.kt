package com.example.worldskillsapp.domain.repository

import com.example.worldskillsapp.data.remote.responses.SendCodeResponse

interface ISignInRepository {
    suspend fun sendCode(email: String): SendCodeResponse?
}