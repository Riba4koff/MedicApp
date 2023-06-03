package com.example.worldskillsapp.di

import com.example.worldskillsapp.presentation.view.SplashScreen
import com.example.worldskillsapp.presentation.viewModels.InputUserDataViewModel
import com.example.worldskillsapp.presentation.viewModels.SignInViewModel
import com.example.worldskillsapp.presentation.viewModels.SplashScreenViewModel
import com.example.worldskillsapp.presentation.viewModels.StartScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewmodels_module = module {
    viewModelOf(::StartScreenViewModel)
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::SignInViewModel)
    viewModelOf(::InputUserDataViewModel)
}