package com.example.worldskillsapp.presentation.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.worldskillsapp.data.local.preferencesDataStore.IDataStore
import kotlinx.coroutines.launch

class StartScreenViewModel(
    private val dataStore: IDataStore
): ViewModel() {
    fun setOnBoardValue(value: Boolean) {
        viewModelScope.launch {
            dataStore.setOnBoardValue(value)
        }
    }
}