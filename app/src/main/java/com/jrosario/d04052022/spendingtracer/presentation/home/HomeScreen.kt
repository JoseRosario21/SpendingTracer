package com.jrosario.d04052022.spendingtracer.presentation.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jrosario.d04052022.spendingtracer.domain.model.Spending
import com.jrosario.d04052022.spendingtracer.presentation.Screen
import com.jrosario.d04052022.spendingtracer.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: HomeViewModel
) {
    val spendings by viewModel.spendings.collectAsState()
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val refreshTrigger = savedStateHandle?.getStateFlow("shouldRefresh", false)?.collectAsState()

    LaunchedEffect(refreshTrigger?.value) {
        if (refreshTrigger?.value == true) {
            viewModel.loadSpendings()
            savedStateHandle["shouldRefresh"] = false
        }
    }
    Scaffold(
        topBar = { TopAppBar(title = { Text("Spending Tracer") }) },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(Screen.AddSpending.route)
            }) {
                Icon(painter = painterResource(id = R.drawable.ic_add_expense), contentDescription = "Add expense")
            }
        }
    ) { padding ->
        if (spendings.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No spendings yet.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(spendings.size) { index ->
                    SpendingItem(spendings[index])
                }
            }
        }
    }
}

@Composable
fun SpendingItem(spending: Spending) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = spending.category.name, style = MaterialTheme.typography.titleMedium)
            Text(text = "€${spending.amount}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Date: ${spending.date}", style = MaterialTheme.typography.bodySmall)
        }
    }
}