package com.example.worldskillsapp.di

import com.example.worldskillsapp.data.remote.repository.SignInRepository
import com.example.worldskillsapp.domain.repository.ISignInRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val repository_module = module {
    singleOf(::SignInRepository){
        bind<ISignInRepository>()
    }
}