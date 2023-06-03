package com.example.worldskillsapp.domain.models

interface MaleModel {
    val value: String

    data class Man(override val value: String = "Мужской") : MaleModel
    data class Women(override val value: String = "Женский") : MaleModel
    data class Unselected(override val value: String = "Не выбрано") : MaleModel
}