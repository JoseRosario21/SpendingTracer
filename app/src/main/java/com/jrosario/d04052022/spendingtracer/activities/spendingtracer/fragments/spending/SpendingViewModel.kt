package com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.spending

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jrosario.d04052022.spendingtracer.models.SpendingLog
import com.jrosario.d04052022.spendingtracer.storage.AppDatabase
import kotlinx.coroutines.launch

class SpendingViewModel(val app: Application) : AndroidViewModel(app) {
    private val db = AppDatabase.getDatabase(app)
    private val dao = db.dao()

    private val _spendingLogs = MutableLiveData<List<SpendingLog>>()
    val spendingLogs: LiveData<List<SpendingLog>>
        get() = _spendingLogs

    fun getSpendingLogs() {
        viewModelScope.launch {
            val logs = dao.getSpendingLogs()
            _spendingLogs.value = logs
        }
    }
}