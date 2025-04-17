package com.example.tuan03_bai01.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.tuan03_bai01.ui.screens.ComponentDetailScreen
import com.example.tuan03_bai01.ui.screens.ComponentsListScreen
import com.example.tuan03_bai01.ui.screens.WelcomeScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = Screen.Welcome.route) {
        composable(Screen.Welcome.route) { WelcomeScreen(navController) }
        composable(Screen.ComponentsList.route) { ComponentsListScreen(navController) }
        composable(
            route = Screen.ComponentDetail.route, // Sử dụng route từ Screen sealed class
            // Định nghĩa argument "componentName" mà ViewModel sẽ sử dụng
            arguments = listOf(navArgument("componentName") {
                type = NavType.StringType
                // Có thể thêm nullable = false hoặc defaultValue nếu cần, nhưng ViewModel đã xử lý null rồi
            })
        ) {
            // 🔥 Sửa lại lời gọi ComponentDetailScreen:
            // Không cần lấy componentName từ backStackEntry ở đây và truyền vào nữa.
            // Chỉ cần truyền navController. ViewModel sẽ tự lấy argument.
            ComponentDetailScreen(navController = navController)
        }
    }
}