package com.jrosario.d04052022.spendingtracer.presentation.addspending

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.jrosario.d04052022.spendingtracer.domain.model.SpendingCategory

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddSpendingScreen(
    navController: NavController,
    viewModel: AddSpendingViewModel = hiltViewModel()
) {
    val categories = SpendingCategory.entries
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Add Spending", style = MaterialTheme.typography.headlineMedium)

        OutlinedTextField(
            value = viewModel.name,
            onValueChange = { viewModel.name = it },
            label = { Text("Name") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = viewModel.amount,
            onValueChange = { viewModel.amount = it },
            label = { Text("Amount") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Box(modifier = Modifier.fillMaxWidth()) {
            OutlinedTextField(
                value = viewModel.selectedCategory?.name ?: "Select Category",
                onValueChange = {},
                label = { Text("Category") },
                modifier = Modifier.fillMaxWidth(),
                readOnly = true,
                trailingIcon = {
                    Icon(Icons.Default.ArrowDropDown, contentDescription = null)
                }
            )
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                categories.forEach { category ->
                    DropdownMenuItem(
                        text = { Text(category.name) },
                        onClick = {
                            viewModel.selectedCategory = category
                            expanded = false
                        }
                    )
                }
            }
            Spacer(
                modifier = Modifier
                    .matchParentSize()
                    .clickable { expanded = true }
            )
        }

        if (viewModel.error != null) {
            Text(viewModel.error!!, color = MaterialTheme.colorScheme.error)
        }

        Spacer(modifier = Modifier.padding(16.dp))

        Row {
            OutlinedButton(
                onClick = {
                    navController.popBackStack()
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Cancel")
            }

            Spacer(modifier = Modifier.padding(8.dp))

            Button(
                onClick = {
                    viewModel.save {
                        navController.previousBackStackEntry
                            ?.savedStateHandle
                            ?.set("shouldRefresh", true)

                        navController.popBackStack()
                    }
                },
                modifier = Modifier.weight(1f)
            ) {
                Text("Save")
            }
        }
    }
}
