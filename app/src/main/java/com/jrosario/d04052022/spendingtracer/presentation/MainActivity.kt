package com.jrosario.d04052022.spendingtracer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jrosario.d04052022.spendingtracer.presentation.addspending.AddSpendingScreen
import com.jrosario.d04052022.spendingtracer.presentation.home.HomeScreen
import com.jrosario.d04052022.spendingtracer.presentation.theme.SpendingTracerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpendingTracerTheme {
                AppNavGraph()
            }
        }
    }
}

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController()
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController = navController, viewModel = hiltViewModel())
            }
            composable(Screen.AddSpending.route) {
                AddSpendingScreen(navController = navController, viewModel = hiltViewModel())
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object Home : Screen("home")
    data object AddSpending : Screen("add_spending")
}