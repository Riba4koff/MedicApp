package com.example.worldskillsapp.data.local.preferencesDataStore

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

/*
* Создатель: Павел Рыбаков
* Время создания: May 30, 14:30
* Назначение: хранение данных, которые смогут пережить смерть активити. Проще говоря - жесткий диск
*/
class DataStore(context: Context) : IDataStore {
    private val dataStore = context.dataStore
    override suspend fun setOnBoardValue(value: Boolean) {
        dataStore.updateData { settings ->
            settings.copy(onBoardInfo = value)
        }
    }

    override suspend fun getInfoAboutOnBoard(): Flow<Boolean> = dataStore.data.map { settings ->
        settings.onBoardInfo
    }

    override suspend fun setUserPassword(password: String) {
        dataStore.updateData { settings ->
            settings.copy(password = password)
        }
    }

    override suspend fun getUserPassword(password: String): Flow<String> =
        dataStore.data.map { settings ->
            settings.password
        }

    override suspend fun saveSignInCode(code: String) {
        dataStore.updateData { settings ->
            settings.copy(signInCode = code)
        }
    }

    override suspend fun getSignInCode(): Flow<String> = dataStore.data.map {
        it.signInCode
    }

    override suspend fun getUserPassword(): Flow<String> = dataStore.data.map {
        it.password
    }

    override suspend fun saveUserPassword(password: String) {
        dataStore.updateData { settings ->
            settings.copy(password = password)
        }
    }
}