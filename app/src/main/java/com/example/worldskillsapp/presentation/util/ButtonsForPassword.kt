package com.example.worldskillsapp.presentation.util

sealed class ButtonsForPassword(val data: String) {
    object Zero: ButtonsForPassword(data = "0")
    object First: ButtonsForPassword(data = "1")
    object Second: ButtonsForPassword(data = "2")
    object Third: ButtonsForPassword(data = "3")
    object Fourth: ButtonsForPassword(data = "4")
    object Fifth: ButtonsForPassword(data = "5")
    object Sixth: ButtonsForPassword(data = "6")
    object Seventh: ButtonsForPassword(data = "7")
    object Eighth: ButtonsForPassword(data = "8")
    object Nineth: ButtonsForPassword(data = "9")
}
