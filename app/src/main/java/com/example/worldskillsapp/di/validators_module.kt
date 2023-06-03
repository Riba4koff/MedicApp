package com.example.worldskillsapp.di

import com.example.worldskillsapp.domain.validators.CredentialValidator
import com.example.worldskillsapp.domain.validators.DateValidator
import com.example.worldskillsapp.domain.validators.EmailValidator
import com.example.worldskillsapp.domain.validators.Validator
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val validators_module = module {
    singleOf(::EmailValidator) {
        bind<Validator<String>>()
    }
    singleOf(::DateValidator){
        bind<Validator<String>>()
    }
    singleOf(::CredentialValidator){
        bind<Validator<String>>()
    }
}