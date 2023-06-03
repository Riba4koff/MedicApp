package com.example.worldskillsapp.data.local.preferencesDataStore

import kotlinx.coroutines.flow.Flow
/*
* Создатель: Павел Рыбаков
* Время создания: 14:30
* Назначение: интерфейс для класса DataStore
*/
interface IDataStore {
    suspend fun setOnBoardValue(value: Boolean)
    suspend fun getInfoAboutOnBoard(): Flow<Boolean>

    suspend fun setUserPassword(password: String)
    suspend fun getUserPassword(password: String): Flow<String>

    suspend fun saveSignInCode(code: String)
    suspend fun getSignInCode(): Flow<String>

    suspend fun saveUserPassword(password: String)
    suspend fun getUserPassword(): Flow<String>
}