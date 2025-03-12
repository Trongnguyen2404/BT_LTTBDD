package com.example.lttbdd_t2_b2.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Book
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.example.lttbdd_t2_b2.ui.theme.BooksScreen
import com.example.lttbdd_t2_b2.ui.theme.BorrowScreen
import com.example.lttbdd_t2_b2.ui.theme.LibraryViewModel
import com.example.lttbdd_t2_b2.ui.theme.UsersScreen

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    object Books : Screen("books", Icons.Filled.Book, "Sách")
    object Users : Screen("users", Icons.Filled.Person, "Người dùng")
    object Borrow : Screen("borrow", Icons.Filled.List, "Đang mượn")
}

val bottomNavItems = listOf(
    Screen.Books,
    Screen.Users,
    Screen.Borrow
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val viewModel = remember { LibraryViewModel() }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    val currentRoute = getCurrentRoute(navController)
                    val screen = bottomNavItems.find { it.route == currentRoute } ?: Screen.Books
                    Text(screen.title)
                }
            )
        },
        bottomBar = {
            NavigationBar {
                val currentRoute = getCurrentRoute(navController)
                bottomNavItems.forEach { screen ->
                    NavigationBarItem(
                        icon = { Icon(screen.icon, contentDescription = screen.title) },
                        label = { Text(screen.title) },
                        selected = currentRoute == screen.route,
                        onClick = {
                            navController.navigate(screen.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = Screen.Books.route,
            modifier = androidx.compose.ui.Modifier.padding(innerPadding)
        ) {
            composable(Screen.Books.route) {
                BooksScreen(viewModel)
            }
            composable(Screen.Users.route) {
                UsersScreen(viewModel)
            }
            composable(Screen.Borrow.route) {
                BorrowScreen(viewModel)
            }
        }
    }
}

// Hàm tiện ích lấy route hiện tại
@Composable
fun getCurrentRoute(navController: NavHostController): String? {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}
