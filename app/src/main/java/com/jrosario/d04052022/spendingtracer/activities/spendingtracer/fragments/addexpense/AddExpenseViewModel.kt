package com.jrosario.d04052022.spendingtracer.activities.spendingtracer.fragments.addexpense

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.jrosario.d04052022.spendingtracer.models.SpendingLog
import com.jrosario.d04052022.spendingtracer.storage.AppDatabase
import kotlinx.coroutines.launch
import org.joda.time.DateTime

class AddExpenseViewModel(val app: Application) : AndroidViewModel(app) {
    private val db = AppDatabase.getDatabase(app)
    private val dao = db.dao()

    fun addExpense(name: String, amount: Double, selectedCategory: Int, selectedPaymentMethod: Int) {
        viewModelScope.launch {
            val spendingLog = SpendingLog(
                name = name,
                amount = amount,
                category = selectedCategory,
                paymentMethod = selectedPaymentMethod,
                date = DateTime.now().toString("dd/MM/yyyy")
            )
            dao.insertSpendingLog(spendingLog)

            val accountLog = dao.getAccountLog()

            when (selectedPaymentMethod) {
                0 -> accountLog.bankAmount = accountLog.bankAmount.minus(spendingLog.amount)
                1 -> accountLog.cashAmount = accountLog.cashAmount.minus(spendingLog.amount)
                2 -> accountLog.foodAllowanceAmount = accountLog.foodAllowanceAmount.minus(spendingLog.amount)
                3 -> accountLog.savingAmount = accountLog.savingAmount.minus(spendingLog.amount)
            }

            dao.insertAccountLog(accountLog)
        }
    }
}