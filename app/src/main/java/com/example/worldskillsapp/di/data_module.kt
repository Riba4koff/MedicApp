package com.example.worldskillsapp.di

import androidx.room.Room
import com.example.worldskillsapp.WorldSkillsApp
import com.example.worldskillsapp.data.local.preferencesDataStore.DataStore
import com.example.worldskillsapp.data.local.preferencesDataStore.IDataStore
import com.example.worldskillsapp.data.local.room.WorldSkillsDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val data_module = module {
    singleOf(::DataStore){
        bind<IDataStore>()
    }
    single {
        Room.databaseBuilder(
            androidContext(),
            WorldSkillsDataBase::class.java,
            WorldSkillsDataBase.DB_NAME
        ).fallbackToDestructiveMigration().build().dao()
    }
}