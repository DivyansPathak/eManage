package com.example.emanage.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "expense_table")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val type : String, // credit or debit
    val amount : Int,
    val date : LocalDate,
    val note : String? = null

    )
