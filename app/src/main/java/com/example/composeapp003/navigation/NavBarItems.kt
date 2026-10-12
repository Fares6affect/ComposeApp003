package com.example.composeapp003.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircleOutline
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person

object NavBarItems {
    val BarItems = listOf(
        BarItem(
            "Главная",
            Icons.Default.Home,
            Routes.HOME
        ),
        BarItem(
            "Завершенные",
            Icons.Default.CheckCircleOutline,
            Routes.COMPLETE_TASK
        ),
        BarItem(
            "Профиль",
            Icons.Default.Person,
            Routes.PROFILE
        )
    )
}