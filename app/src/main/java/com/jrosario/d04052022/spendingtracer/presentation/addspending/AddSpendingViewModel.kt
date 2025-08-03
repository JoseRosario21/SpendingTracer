package com.jrosario.d04052022.spendingtracer.presentation.addspending

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrosario.d04052022.spendingtracer.domain.model.Spending
import com.jrosario.d04052022.spendingtracer.domain.model.SpendingCategory
import com.jrosario.d04052022.spendingtracer.domain.repository.SpendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import jakarta.inject.Inject
import kotlinx.coroutines.launch
import java.time.LocalDate

@HiltViewModel
class AddSpendingViewModel @Inject constructor(
    private val repository: SpendingRepository
) : ViewModel() {

    var name by mutableStateOf("")
    var amount by mutableStateOf("")
    var selectedCategory by mutableStateOf<SpendingCategory?>(null)
    var error by mutableStateOf<String?>(null)

    fun save(onSuccess: () -> Unit) {
        val nameTrimmed = name.trim()
        val amountValue = amount.toDoubleOrNull()

        if (nameTrimmed.isEmpty() || amountValue == null || selectedCategory == null) {
            error = "Please fill in all fields correctly."
            return
        }

        val spending = Spending(
            name = nameTrimmed,
            amount = amountValue,
            category = selectedCategory!!,
            date = LocalDate.now().toString()
        )

        viewModelScope.launch {
            repository.insert(spending)
            onSuccess()
        }
    }
}
