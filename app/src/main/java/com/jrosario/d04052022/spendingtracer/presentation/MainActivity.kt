package com.jrosario.d04052022.spendingtracer.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.jrosario.d04052022.spendingtracer.R
import com.jrosario.d04052022.spendingtracer.presentation.addspending.AddSpendingScreen
import com.jrosario.d04052022.spendingtracer.presentation.graph.GraphScreen
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
        Scaffold(
            bottomBar = {
                BottomNavigationBar(navController)
            }
        ) { innerPadding ->
            NavHost(
                navController = navController,
                startDestination = BottomNavItem.Home.route,
                modifier = Modifier.padding(innerPadding)
            ) {
                composable(BottomNavItem.Home.route) {
                    HomeScreen(navController, viewModel = hiltViewModel())
                }
                composable(BottomNavItem.Graph.route) {
                    GraphScreen(viewModel = hiltViewModel())
                }
                composable(Screen.AddSpending.route) {
                    AddSpendingScreen(navController = navController, viewModel = hiltViewModel())
                }
            }
        }
    }
}

@Composable
fun BottomNavigationBar(navController: NavHostController) {
    val items = listOf(BottomNavItem.Home, BottomNavItem.Graph)
    val currentDestination = navController.currentBackStackEntryAsState().value?.destination

    NavigationBar {
        items.forEach { item ->
            NavigationBarItem(
                label = { Text(item.label) },
                icon = { Icon(painter = painterResource(id = item.icon), contentDescription = item.label) },
                selected = currentDestination?.route == item.route,
                onClick = {
                    if (currentDestination?.route != item.route) {
                        navController.navigate(item.route) {
                            popUpTo(BottomNavItem.Home.route)
                            launchSingleTop = true
                        }
                    }
                }
            )
        }
    }
}

sealed class BottomNavItem(val route: String, val label: String, @param:DrawableRes val icon: Int) {
    data object Home : BottomNavItem("home", "Spendings", R.drawable.ic_home)
    data object Graph : BottomNavItem("graph", "Statistics", R.drawable.ic_pie_chart)
}

sealed class Screen(val route: String) {
    data object AddSpending : Screen("add_spending")
}