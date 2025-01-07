package com.example.emanage.Model

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ExpenseViewModel(application: Application) : AndroidViewModel(application){
    private val expenseDao : ExpenseDAO = ExpenseDatabase.getDatabase(application).expenseDao()
    val allExpense : LiveData<List<Expense>> = expenseDao.getAllExpense()

    fun addExpense(expense: Expense){
        viewModelScope.launch {
            try {
                expenseDao.insertExpense(expense)
                Log.d("ExpenseViewModel", "Expense added: $expense")
            } catch (e: Exception) {
                Log.e("ExpenseViewModel", "Error adding expense", e)
            }
        }

    }


    fun deleteExpense(expense: Expense){
        viewModelScope.launch {
            try {
                expenseDao.deleteExpense(expense)
                Log.d("ExpenseViewModel", "Expense deleted: $expense")
            } catch (e: Exception) {
                Log.e("ExpenseViewModel", "Error deleting expense", e)}
        }
    }



}