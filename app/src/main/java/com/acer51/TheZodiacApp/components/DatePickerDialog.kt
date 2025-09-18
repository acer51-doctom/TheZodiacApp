package com.acer51.TheZodiacApp.components

import android.app.DatePickerDialog
import android.content.Context
import java.time.LocalDate
import java.util.*

fun showDatePicker(
    context: Context,
    onDateSelected: (LocalDate) -> Unit
) {
    val calendar = Calendar.getInstance()
    val year = calendar.get(Calendar.YEAR)
    val month = calendar.get(Calendar.MONTH)
    val day = calendar.get(Calendar.DAY_OF_MONTH)

    DatePickerDialog(
        context,
        { _, selectedYear, selectedMonth, selectedDay ->
            val date = LocalDate.of(selectedYear, selectedMonth + 1, selectedDay)
            onDateSelected(date)
        },
        year, month, day
    ).show()
}
