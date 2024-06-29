package com.example.worldofplantsapp.presentation.graphMain.bottom.navigation

import androidx.annotation.DrawableRes
import com.example.worldofplantsapp.R

enum class BottomIcon(
    @DrawableRes val icon: Int,
    val title: String,
    val route: String,
    val iconFocused: Int,
) {
    Home(
        icon = R.drawable.home_icon_plants,
        iconFocused = R.drawable.home_icon_focused,
        title = "Главная",
        route = "main_screen",
    ),
    Search(
        icon = R.drawable.search_icon_plants,
        iconFocused = R.drawable.search_icon_focused,
        title = "Поиск",
        route = "search_screen",
    ),
    Plant(
        icon = R.drawable.plant_icon,
        iconFocused = R.drawable.plant_icon_focused,
        title = "Мой сад",
        route = "plant_screen",
    ),
    Align(
        icon = R.drawable.align_icon_plant,
        iconFocused = R.drawable.align_icon_focused,
        title = "Советы",
        route = "align_screen",
    ),
    Profile(
        icon = R.drawable.profile_icon_plants,
        iconFocused = R.drawable.profile_icon_focused,
        title = "Профиль",
        route = "profile_screen",
    )
}