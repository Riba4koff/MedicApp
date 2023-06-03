package com.example.worldskillsapp.di

import com.example.worldskillsapp.domain.usecases.SaveCodeUseCase
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val usecases_module = module {
    factoryOf(::SaveCodeUseCase)
}