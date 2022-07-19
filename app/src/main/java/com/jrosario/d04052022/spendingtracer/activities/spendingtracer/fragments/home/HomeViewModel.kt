package com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.jrosario.d04052022.spendingtracer.models.AccountLog
import com.jrosario.d04052022.spendingtracer.storage.AppDatabase
import kotlinx.coroutines.launch

class HomeViewModel(val app: Application) : AndroidViewModel(app) {
    private val _accountLog = MutableLiveData<AccountLog>()
    val accountLog: LiveData<AccountLog>
        get() = _accountLog

    private val db = AppDatabase.getDatabase(app)
    private val dao = db.dao()

    private fun insertAccountLog(accountLog: AccountLog) {
        viewModelScope.launch {
            dao.insertAccountLog(accountLog)
        }
    }

    fun getAccountLogFromDB() {
        viewModelScope.launch {
            val log: AccountLog = dao.getAccountLog() ?: AccountLog()

            insertAccountLog(log)
            _accountLog.value = log
        }
    }

    fun sum(accountLog: AccountLog): Double =
        accountLog.bankAmount + accountLog.cashAmount + accountLog.foodAllowanceAmount + accountLog.savingAmount

    fun addMoneyToAccount(amount: Double, selectedMoneyCategory: Int) {
        val log: AccountLog = _accountLog.value!!
        when (selectedMoneyCategory) {
            0 -> log.bankAmount = log.bankAmount.plus(amount)
            1 -> log.cashAmount = log.cashAmount.plus(amount)
            2 -> log.foodAllowanceAmount = log.foodAllowanceAmount.plus(amount)
            3 -> log.savingAmount = log.savingAmount.plus(amount)
        }

        _accountLog.value = log
        insertAccountLog(log)
    }
}