package com.example.worldskillsapp.domain.models

import com.example.worldskillsapp.R

enum class ScreenPagerModel(
    val index: Int,
    val title: String,
    val description: String,
    val image: Int
) {
    FirstPage(index = 0, title = "Анализы", description = "Экспресс сбор и получение проб", image = R.drawable.first_image_of_pager),
    SecondPage(index = 1, title = "Уведомления", description = "Вы быстро знаете о результатах", image = R.drawable.second_image_of_pager),
    ThirdPage(index = 2, title = "Мониторинг", description = "Наши врачи всегда наблюдают за показателями вашего здоровья", image = R.drawable.third_image_of_pager)
}
