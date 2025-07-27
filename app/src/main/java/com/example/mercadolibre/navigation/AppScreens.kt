package com.example.mercadolibre.navigation

sealed class AppScreens(val route: String) {
    object MainScreen: AppScreens("main_screen")
}