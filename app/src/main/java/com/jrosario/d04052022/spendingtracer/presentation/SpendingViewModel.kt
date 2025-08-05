package com.jrosario.d04052022.spendingtracer.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrosario.d04052022.spendingtracer.domain.model.Spending
import com.jrosario.d04052022.spendingtracer.domain.model.SpendingCategory
import com.jrosario.d04052022.spendingtracer.domain.repository.SpendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SpendingViewModel @Inject constructor(
    private val repository: SpendingRepository
) : ViewModel() {

    private val _spending = MutableStateFlow<List<Spending>>(emptyList())
    val spending: StateFlow<List<Spending>> = _spending.asStateFlow()

    private val _data = MutableStateFlow<Map<SpendingCategory, Double>>(mutableMapOf())
    val data: StateFlow<Map<SpendingCategory, Double>> = _data.asStateFlow()

    init {
        loadSpending()
    }

    fun loadSpending() {
        viewModelScope.launch {
            _spending.value = repository.getAll()
            getSpendingDataByCategory()
        }
    }

    fun getSpendingDataByCategory() {
        val spendingDataByCategory = mutableMapOf<SpendingCategory, Double>()
        _spending.value.forEach {
            spendingDataByCategory[it.category] = spendingDataByCategory.getOrDefault(it.category, 0.0) + it.amount
        }
        _data.value = spendingDataByCategory
    }
}