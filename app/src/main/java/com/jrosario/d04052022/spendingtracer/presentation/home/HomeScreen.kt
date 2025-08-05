package com.jrosario.d04052022.spendingtracer.presentation.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.jrosario.d04052022.spendingtracer.R
import com.jrosario.d04052022.spendingtracer.domain.model.Spending
import com.jrosario.d04052022.spendingtracer.presentation.Screen
import com.jrosario.d04052022.spendingtracer.presentation.SpendingViewModel
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: SpendingViewModel
) {
    val spending by viewModel.spending.collectAsState()
    val savedStateHandle = navController.currentBackStackEntry?.savedStateHandle
    val refreshTrigger = savedStateHandle?.getStateFlow("shouldRefresh", false)?.collectAsState()

    LaunchedEffect(refreshTrigger?.value) {
        if (refreshTrigger?.value == true) {
            viewModel.loadSpending()
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
        if (spending.isEmpty()) {
            Box(modifier = Modifier
                .fillMaxSize()
                .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Text("No spending yet.")
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding)
            ) {
                items(spending.size) { index ->
                    SpendingItem(spending[index])
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
            Row {
                Text(text = spending.category.name, style = MaterialTheme.typography.titleMedium)
                Icon(painter = painterResource(id = spending.category.icon), contentDescription = "Category Icon")
            }
            Text(text = spending.name, style = MaterialTheme.typography.titleMedium)
            Text(text = String.format(Locale.getDefault(), "€%.2f", spending.amount), style = MaterialTheme.typography.bodyLarge)
            Text(text = "Date: ${spending.date}", style = MaterialTheme.typography.bodySmall)
        }
    }
}