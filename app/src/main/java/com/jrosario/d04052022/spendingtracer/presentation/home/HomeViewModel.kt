package com.jrosario.d04052022.spendingtracer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrosario.d04052022.spendingtracer.domain.model.Spending
import com.jrosario.d04052022.spendingtracer.domain.model.SpendingCategory
import com.jrosario.d04052022.spendingtracer.domain.repository.SpendingRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: SpendingRepository
) : ViewModel() {

    private val _spendings = MutableStateFlow<List<Spending>>(emptyList())
    val spendings: StateFlow<List<Spending>> = _spendings.asStateFlow()

    private val _data = MutableStateFlow<Map<SpendingCategory, Double>>(mutableMapOf())
    val data: StateFlow<Map<SpendingCategory, Double>> = _data.asStateFlow()

    init {
        loadSpendings()
    }

    fun loadSpendings() {
        viewModelScope.launch {
            _spendings.value = repository.getAll()
            getSpendingDataByCategory()
        }
    }

    fun getSpendingDataByCategory() {
        val spendingDataByCategory = mutableMapOf<SpendingCategory, Double>()
        _spendings.value.forEach {
            spendingDataByCategory[it.category] = spendingDataByCategory.getOrDefault(it.category, 0.0) + it.amount
        }
        _data.value = spendingDataByCategory
    }
}