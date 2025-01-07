package com.example.emanage.View

import android.widget.DatePicker
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.emanage.Model.ExpenseViewModel
import java.time.Instant
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.DateTimeFormatter


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompBottomSheet(onDismiss: () -> Unit) {

//    var showBottomsheet by remember {mutableStateOf(false)}

    val bottomSheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    ModalBottomSheet(
        onDismissRequest = { onDismiss() },
        sheetState = bottomSheetState
    ) {
        Text(
            text = "Add you expense",
            modifier = Modifier.padding(8.dp),
            style = MaterialTheme.typography.headlineSmall
        )

        BottomSheetContent(onDismiss)
    }
}

@Composable
fun OptionButton(
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.surface
    val textColor =
        if (isSelected) MaterialTheme.colorScheme.onPrimary else MaterialTheme.colorScheme.onSurface

    Box(
        modifier = Modifier
            .padding(8.dp)
            .height(50.dp)
            .width(200.dp)
            .clip(RoundedCornerShape(8.dp))
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(8.dp),
        contentAlignment = Alignment.Center
    ) {

        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = textColor,
        )
    }
}

@Composable
fun BottomSheetContent(onDismiss: () -> Unit) {

    var selectedOption by remember { mutableStateOf<String?>(null) } // for credit and debit option button

    var showDatePicker by remember { mutableStateOf(false) }
    var selectedDate by remember { mutableStateOf<LocalDate?>(null) }
    val dateFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy")

    val expenseVieModel: ExpenseViewModel = viewModel()

    var amountText by remember { mutableStateOf("") }
    var noteText by remember { mutableStateOf("") }

    val context = LocalContext.current


    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 8.dp, end = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                OptionButton(text = "Credit", isSelected = selectedOption == "Credit") {
                    selectedOption = "Credit"
                }
                OptionButton(text = "Debit", isSelected = selectedOption == "Debit") {
                    selectedOption = "Debit"
                }
            }

            Spacer(modifier = Modifier.height(8.dp))

            Box(
                modifier = Modifier
                    .padding(8.dp) // Adjust spacing between buttons
                    .height(50.dp) // Set height
                    .width(200.dp) // Set width
                    .clip(RoundedCornerShape(8.dp)) // Rounded corners
                    .background(MaterialTheme.colorScheme.surface)
                    .clickable(onClick = { showDatePicker = true })
                    .padding(8.dp), // Adjust inner padding
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = selectedDate?.format(dateFormatter) ?: "Select Date",
                    style = MaterialTheme.typography.bodyLarge,

                    )
            }



            CompTetBox("Amount", "Rupee", keyboardType = KeyboardType.Number) {
                val amount = it.toIntOrNull() ?: 0
                amountText = amount.toString()


            }
            Spacer(modifier = Modifier.height(48.dp))
            CompTetBox(label = "Note", placeholder = "Add Note", keyboardType = KeyboardType.Text) {
                val note = it.toString()
                noteText = note

            }
            Spacer(modifier = Modifier.height(16.dp))

            Text(text = "Save",
                fontSize = 20.sp,
                color = MaterialTheme.colorScheme.secondary,
                modifier = Modifier
                    .clickable {
                        if (selectedOption != null && selectedDate != null && amountText.isNotEmpty()) {
                            val expense = com.example.emanage.Model.Expense(
                                type = selectedOption!!,
                                amount = amountText.toInt(),
                                date = selectedDate!!,
                                note = noteText
                            )
                            expenseVieModel.addExpense(expense)
                        }
//                        Toast.makeText(context,"$amountText and $noteText", Toast.LENGTH_SHORT).show()

                        onDismiss()

                    }


            )
        }
    }

    if (showDatePicker) {
        CompDatePickerDialog(
            onDismiss = { showDatePicker = false },
            onselectedDate = { date ->
                selectedDate = date
                showDatePicker = false
            }
        )
    }

}

@Composable
fun CompTetBox(
    label: String,
    placeholder: String,
    keyboardType: KeyboardType,
    onTextChange: (String) -> Unit
) {

    var text by remember { mutableStateOf("") }

    OutlinedTextField(
        modifier = Modifier.width(300.dp), value = text,
        onValueChange = { newText ->
            text = newText
            onTextChange(newText)
        },
        label = { Text(label) },
        placeholder = { Text(text = placeholder) },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType,
            imeAction = ImeAction.Done
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = MaterialTheme.colorScheme.secondary,
            unfocusedBorderColor = MaterialTheme.colorScheme.primary,
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedLabelColor = MaterialTheme.colorScheme.primary,
            unfocusedLabelColor = MaterialTheme.colorScheme.primary,
            focusedPlaceholderColor = MaterialTheme.colorScheme.primary,
            unfocusedPlaceholderColor = MaterialTheme.colorScheme.primary
        )
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CompDatePickerDialog(
    onDismiss: () -> Unit,
    onselectedDate: (LocalDate) -> Unit
) {
    val datePickerState = rememberDatePickerState()

    DatePickerDialog(onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val epochMilli = datePickerState.selectedDateMillis
                if (epochMilli != null) {
                    val localDate = Instant.ofEpochMilli(epochMilli)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate()
                    onselectedDate(localDate)
                }
            }) {
                Text(text = "Ok")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text(text = "Cancel")
            }
        }
    ) {
        DatePicker(state = datePickerState)


    }

}


