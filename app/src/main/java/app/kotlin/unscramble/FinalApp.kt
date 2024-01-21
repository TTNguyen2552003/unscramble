package app.kotlin.unscramble

import android.os.Build
import androidx.activity.compose.BackHandler
import androidx.annotation.RequiresExtension
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import app.kotlin.unscramble.ui.screens.GameScreen
import app.kotlin.unscramble.ui.screens.HomeScreen
import app.kotlin.unscramble.ui.screens.InputNameScreen
import app.kotlin.unscramble.ui.screens.LeaderBoardScreen
import app.kotlin.unscramble.ui.screens.TutorialScreen

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 7)
@Composable
fun FinalApp() {
    BackHandler(enabled = true, onBack = {})

    val navController: NavHostController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = "HomeScreen"
    ) {
        composable(route = "HomeScreen") {
            HomeScreen(navController = navController)
        }
        composable(route = "InputNameScreen"){
            InputNameScreen(navController = navController)
        }
        composable(route = "GameScreen") {
            GameScreen(navController = navController)
        }
        composable(route = "LeaderBoardScreen") {
            LeaderBoardScreen(navController = navController)
        }
        composable(route = "TutorialScreen") {
            TutorialScreen(navController = navController)
        }
    }
}