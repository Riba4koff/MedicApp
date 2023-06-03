package com.example.worldskillsapp.data.local.preferencesDataStore

import android.content.Context
import androidx.datastore.dataStore

val Context.dataStore by dataStore(
    "proto_datastore",
    SettingsSerializer
)