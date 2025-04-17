package com.example.tuan03_bai01.navigation

sealed class Screen(val route: String) {
    object Welcome : Screen("welcome")
    object ComponentsList : Screen("components_list")
    object ComponentDetail : Screen("component_detail/{componentName}") {
        fun createRoute(componentName: String) = "component_detail/$componentName"
    }
}
