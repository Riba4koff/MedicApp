package com.example.worldskillsapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskillsapp.data.local.preferencesDataStore.IDataStore
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SplashScreenViewModel(
    private val dataStore: IDataStore
): ViewModel() {

    fun checkOnBoardInfo(isTrue: () -> Unit, isFalse: () -> Unit) {
        viewModelScope.launch {
            dataStore.getInfoAboutOnBoard().first().let { onBoardInfoIsViewable ->
                if (onBoardInfoIsViewable) isTrue()
                else {
                    dataStore.setOnBoardValue(true)
                    isFalse()
                }
            }
        }
    }
}