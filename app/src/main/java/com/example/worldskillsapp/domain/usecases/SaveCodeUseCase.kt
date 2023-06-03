package com.example.worldskillsapp.domain.usecases

import android.util.Log
import com.example.worldskillsapp.data.local.preferencesDataStore.IDataStore
import com.example.worldskillsapp.domain.repository.ISignInRepository

class SaveCodeUseCase(
    private val repository: ISignInRepository,
    private val dataStore: IDataStore
) {
    suspend operator fun invoke(email: String): Result<String> {
        val response = repository.sendCode(email)
        return if (response == null) Result.failure(Exception("Ошибка получения кода"))
        else {
            dataStore.saveSignInCode(response.code)
            Log.e("CODE_API: ", response.code)
            Result.success(response.code)
        }
    }
}