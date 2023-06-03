package com.example.worldskillsapp.di

import com.example.worldskillsapp.data.remote.Api
import com.example.worldskillsapp.data.remote.IApi
import com.example.worldskillsapp.data.remote.KtorHttpClient
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val api_module = module {
    singleOf(::KtorHttpClient)
    singleOf(::Api){
        bind<IApi>()
    }
}