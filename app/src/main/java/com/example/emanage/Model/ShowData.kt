package com.example.emanage.Model

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ExpenseList(expenses: List<Expense>) {
    val expenseViewModel: ExpenseViewModel = viewModel()
//    val expenses by expenseViewModel.allExpense.observeAsState(emptyList())

    LazyColumn(modifier = Modifier
        .fillMaxSize()
        .padding(top = 32.dp)) {
        items(expenses) { expense ->

            ExpenseItem(expense = expense, onDeletion = {
                expenseViewModel.deleteExpense(it)
            })

        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ExpenseItem(expense: Expense,onDeletion : (Expense) -> Unit) {
    var showDeleteDialog by remember { mutableStateOf(false) }

    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

    if (showDeleteDialog){
        AlertDialog(onDismissRequest = { showDeleteDialog = false },
            title = { Text(text = "Delete Expense",
                color = MaterialTheme.colorScheme.secondary,
                style = MaterialTheme.typography.headlineLarge)},
            text = { Text(text = "Are you sure you want to delete this expense?",
                color = MaterialTheme.colorScheme.primary,
                   style = MaterialTheme.typography.headlineSmall) },
            confirmButton = {
                TextButton(onClick = {
                    onDeletion(expense)
                    showDeleteDialog = false
                }) {
                    Text(text = "Delete")
                }
            },
            dismissButton = {
                TextButton(onClick = { showDeleteDialog = false }) {
                    Text(text = "Cancel")
                }
            })
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .combinedClickable(
                onClick = { /* Handle item click */ },
                onLongClick = { showDeleteDialog = true }
            ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                Text(text = expense.type,
                    style = TextStyle(fontWeight = FontWeight.Bold),
                    fontSize = 16.sp,
                    color = if (expense.type == "Credit") MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.secondary)

                Text(text = expense.date.format(dateFormatter))}

            Text(text = "Amount: ${expense.amount}")
            Text(text = "Note: ${expense.note}")

        }
    }
    
}
