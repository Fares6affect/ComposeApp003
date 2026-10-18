package com.example.composeapp003

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.example.composeapp003.navigation.AppNavHost
import com.example.composeapp003.navigation.BottomNavigationBar

@Composable
fun App() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {BottomNavigationBar(navController)},
        containerColor = Color(233, 239, 246),
    ) {
            innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }

}