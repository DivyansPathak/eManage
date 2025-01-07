package com.example.emanage.View

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FilterChip
import androidx.compose.material3.FilterChipDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emanage.Model.ExpenseList
import com.example.emanage.Model.ExpenseViewModel


@Composable
fun CompFilterChip() {

    var selectedSearchBy by remember { mutableStateOf("All") }

    val expenseViewModel: ExpenseViewModel = viewModel()

    val expenses by expenseViewModel.allExpense.observeAsState(emptyList())

    val filteredTransactions = expenses.filter { transaction ->
        when (selectedSearchBy) {
            "Credit" -> transaction.type == "Credit"
            "Debit" -> transaction.type == "Debit"
            else -> true // "All" shows all transactions
        }
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, top = 16.dp)
    ) {

        // FilterChip for "All"
        FilterChip(
            selected = selectedSearchBy == "All",
            onClick = { selectedSearchBy = "All" },
            label = { Text("All") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            )
        )

        // FilterChip for "Credit"
        FilterChip(
            selected = selectedSearchBy == "Credit",
            onClick = { selectedSearchBy = "Credit" },
            label = { Text("Credit") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary,
            )
        )

        // FilterChip for "Debit"
        FilterChip(
            selected = selectedSearchBy == "Debit",
            onClick = { selectedSearchBy = "Debit" },
            label = { Text("Debit") },
            colors = FilterChipDefaults.filterChipColors(
                selectedContainerColor = MaterialTheme.colorScheme.primary,
                selectedLabelColor = MaterialTheme.colorScheme.onPrimary)
        )

    }

    ExpenseList(expenses = filteredTransactions)

}