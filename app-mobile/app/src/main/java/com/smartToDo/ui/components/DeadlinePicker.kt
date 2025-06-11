package com.smartToDo.ui.components

import android.app.DatePickerDialog
import android.content.Context
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.ui.platform.LocalContext
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun DeadlinePicker(
    selectedDate: Long?,
    onDateSelected: (Long) -> Unit
) {
    val context = LocalContext.current

    // Trzymaj aktualną datę
    val calendar = Calendar.getInstance()

    // Dialog do wyboru daty
    val datePickerDialog = remember {
        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                calendar.set(year, month, dayOfMonth)
                onDateSelected(calendar.timeInMillis)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
    }

    // UI – pokazanie przycisku otwierającego dialog
    Button(onClick = { datePickerDialog.show() }) {
        Text(text = selectedDate?.let {
            val sdf = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
            sdf.format(Date(it))
        } ?: "Wybierz termin")
    }
}
