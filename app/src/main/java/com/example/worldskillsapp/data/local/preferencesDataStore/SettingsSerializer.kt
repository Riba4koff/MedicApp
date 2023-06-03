package com.example.worldskillsapp.data.local.preferencesDataStore

import androidx.datastore.core.CorruptionException
import androidx.datastore.core.Serializer
import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.withContext
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.decodeFromStream
import java.io.InputStream
import java.io.OutputStream

object SettingsSerializer : Serializer<Settings> {
    override val defaultValue: Settings
        get() = Settings()

    @OptIn(ExperimentalSerializationApi::class)
    override suspend fun readFrom(input: InputStream): Settings {
        return withContext(Dispatchers.IO + SupervisorJob()) {
            try {
                Json.decodeFromStream(Settings.serializer(), input)
            } catch (e: InvalidProtocolBufferException) {
                throw CorruptionException("Cannot read proto", e)
            }
        }
    }

    override suspend fun writeTo(t: Settings, output: OutputStream) {
        return withContext(Dispatchers.IO) {
            output.write(
                Json.encodeToString(
                    serializer = Settings.serializer(),
                    value = t
                ).encodeToByteArray()
            )
        }
    }
}