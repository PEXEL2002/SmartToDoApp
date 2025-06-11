package com.smartToDo.ui.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import com.smartToDo.model.Task
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun TaskCard(
    task: Task,
    onDoneClick: (Task) -> Unit,
    modifier: Modifier = Modifier
) {
    val shape = RoundedCornerShape(8.dp)
    val dateFormat = remember { SimpleDateFormat("dd.MM.yyyy", Locale.getDefault()) }

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(8.dp)
            .border(1.dp, Color(0xFF228B22), shape)
            .padding(12.dp)
    ) {
        Text(text = task.title, style = MaterialTheme.typography.titleMedium)
        if (!task.description.isNullOrBlank()) {
            Text(text = task.description!!, style = MaterialTheme.typography.bodyMedium)
        }

        Spacer(modifier = Modifier.height(4.dp))

        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "P${task.priority}")
            Text(text = task.deadline?.let { dateFormat.format(Date(it)) } ?: "Brak terminu")
        }

        Spacer(modifier = Modifier.height(6.dp))

        Button(
            onClick = { onDoneClick(task) },
            modifier = Modifier.align(Alignment.End),
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF228B22))
        ) {
            Text("Zakończ Zadanie", color = Color.White)
        }
    }
}
