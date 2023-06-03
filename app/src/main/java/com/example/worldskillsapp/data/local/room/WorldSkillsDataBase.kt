package com.example.worldskillsapp.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.worldskillsapp.data.local.room.entities.TestEntity

@Database(
    entities = [TestEntity::class],
    version = 1,
    exportSchema = false
)
abstract class WorldSkillsDataBase: RoomDatabase() {
    abstract fun dao(): WorldSkillsAppDao
    companion object {
        const val DB_NAME = "world_skills_app_data_base"
    }
}