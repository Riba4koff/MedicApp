package com.example.worldskillsapp.data.local.room.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Entity(tableName = "test_entity")
data class TestEntity(
    @PrimaryKey(autoGenerate = false)
    val test: String
)
