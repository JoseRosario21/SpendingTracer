package com.jrosario.d04052022.spendingtracer.presentation

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jrosario.d04052022.spendingtracer.presentation.addspending.AddSpendingScreen
import com.jrosario.d04052022.spendingtracer.presentation.home.HomeScreen
import com.jrosario.d04052022.spendingtracer.presentation.splash.SplashScreen
import com.jrosario.d04052022.spendingtracer.presentation.theme.SpendingTracerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SpendingTracerTheme {
                val navController = rememberNavController()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    NavHost(navController = navController, startDestination = Screen.Splash.route) {

                        composable(Screen.Splash.route) {
                            SplashScreen(
                                navController
                            )
                        }

                        composable(Screen.Home.route) {
                            HomeScreen(navController, hiltViewModel())
                        }
                        composable(Screen.AddSpending.route) {
                            AddSpendingScreen(navController, hiltViewModel())
                        }
                    }
                }
            }
        }
    }
}

sealed class Screen(val route: String) {
    data object Splash : Screen("splash")
    data object Home : Screen("login")
    data object AddSpending : Screen("add_spending")
}