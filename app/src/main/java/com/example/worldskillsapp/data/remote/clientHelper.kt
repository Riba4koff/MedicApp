package com.example.worldskillsapp.data.remote

import android.util.Log
import com.example.worldskillsapp.data.local.preferencesDataStore.DataStore
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.auth.Auth
import io.ktor.client.features.auth.providers.BearerTokens
import io.ktor.client.features.auth.providers.bearer
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import io.ktor.client.features.observer.ResponseObserver
import io.ktor.http.ContentType
import kotlinx.serialization.json.Json
import org.koin.core.component.KoinComponent

class KtorHttpClient(private val dataStore: DataStore): KoinComponent {
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        isLenient = true
        encodeDefaults = false
    }

    private companion object {
        private const val TAG_HTTP_STATUS_LOGGER = "HTTP_STATUS: "
    }

    val clientHelper: HttpClient
        get() = HttpClient(OkHttp){
            expectSuccess = false

            engine {
                threadsCount = 8
                pipelining = true
            }

            install(Auth){
                bearer {
                    loadTokens {
                        BearerTokens("", "")
                    }
                }
            }

            install(ResponseObserver) {
                onResponse { response ->
                    Log.i(TAG_HTTP_STATUS_LOGGER, "${response.status.value}")
                }
            }

            install(JsonFeature) {
                serializer = KotlinxSerializer(json)
                acceptContentTypes = acceptContentTypes + ContentType.Any
            }
        }
}