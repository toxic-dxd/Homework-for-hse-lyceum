package presentation

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun NavigationSetup() {
    val navController = rememberNavController()
    val viewModel: UserViewModel = viewModel()

    NavHost(navController, startDestination = "main") {
        composable("main") { MainScreen(viewModel, navController) }
        composable("details/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toInt() ?: 0
            UserDetailScreen(viewModel, userId)
        }
    }
}
