package com.jrosario.d04052022.spendingtracer.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jrosario.d04052022.spendingtracer.domain.model.Spending
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

    init {
        viewModelScope.launch {
            val spendings = repository.getAll()
            _spendings.value = spendings
        }
    }
}