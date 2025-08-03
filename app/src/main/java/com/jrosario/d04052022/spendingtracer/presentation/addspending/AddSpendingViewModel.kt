package com.jrosario.d04052022.spendingtracer.presentation.addspending

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrosario.d04052022.spendingtracer.domain.model.Spending
import com.jrosario.d04052022.spendingtracer.domain.repository.SpendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class AddSpendingViewModel @Inject constructor(
    private val repository: SpendingRepository
) : ViewModel() {

    var category by mutableStateOf("")
    var amount by mutableStateOf("")
    var date by mutableLongStateOf(System.currentTimeMillis())

    var errorMessage by mutableStateOf<String?>(null)

    fun saveSpending(onSaved: () -> Unit) {
        val parsedAmount = amount.toDoubleOrNull()
        if (category.isBlank() || parsedAmount == null || parsedAmount <= 0.0) {
            errorMessage = "Please enter valid data"
            return
        }

        val spending = Spending(
            id = 0,
            category = category,
            amount = parsedAmount,
            date = date
        )

        viewModelScope.launch {
            repository.insert(spending)
            onSaved()
        }
    }
}