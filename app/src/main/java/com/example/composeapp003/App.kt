package com.example.composeapp003

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.composeapp003.navigation.AppNavHost
import com.example.composeapp003.navigation.BottomNavigationBar

@Composable
fun App() {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route
    var showDialog by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {BottomNavigationBar(navController)},
        containerColor = Color(233, 239, 246),
        floatingActionButton = {
            if(currentRoute == "home"){
                FloatingActionButton(
                    onClick = { showDialog = true },
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(1.dp),
                    containerColor = Color(35, 149, 215)
                ) {
                    Icon(
                        imageVector = Icons.Filled.Add,
                        contentDescription = "Plus",
                        modifier = Modifier,
                        tint = Color.White
                    )
                }
            }
        }
    ) {
            innerPadding ->
        AppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }

}