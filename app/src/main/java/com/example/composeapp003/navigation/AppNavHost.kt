package com.example.composeapp003.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.composeapp003.feature.completed.CompleteTaskScreen
import com.example.composeapp003.feature.home.HomeScreen
import com.example.composeapp003.feature.profile.ProfileScreean

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    NavHost(
        navController = navController,
        startDestination = Routes.HOME,
        modifier = modifier
    ) {
        composable(Routes.HOME) {
            HomeScreen()
        }
        composable(Routes.COMPLETE_TASK) {
            CompleteTaskScreen()
        }
        composable(Routes.PROFILE) {
            ProfileScreean()
        }
    }
}