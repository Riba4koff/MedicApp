package com.example.worldskillsapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Surface
import androidx.compose.runtime.rememberCoroutineScope
import com.example.worldskillsapp.data.remote.IApi
import com.example.worldskillsapp.data.remote.KtorHttpClient
import com.example.worldskillsapp.presentation.navigation.WorldSkillsMedicAppNavigation
import com.example.worldskillsapp.ui.theme.WorldSkillsAppTheme
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class StartActivity : ComponentActivity() {
    @SuppressLint("CoroutineCreationDuringComposition")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WorldSkillsAppTheme {
                Surface {
                    WorldSkillsMedicAppNavigation()
                }
            }
        }
    }
}