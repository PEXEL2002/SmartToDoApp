package com.smartToDo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smartToDo.model.Task
import com.smartToDo.ui.components.CancelButton
import com.smartToDo.ui.components.ConfirmButton
import com.smartToDo.ui.components.DeadlinePicker
import com.smartToDo.ui.components.PriorityDropdown

@Composable
fun AddTaskScreen(
    onConfirm: (Task) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var priority by remember { mutableStateOf(0) }
    var deadline by remember { mutableStateOf<Long?>(null) }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues()) // od notch / gestów
    ) {
        // Główna część z inputami — przewijalna
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(horizontal = 16.dp, vertical = 24.dp)
                .align(Alignment.TopCenter),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = title,
                onValueChange = { title = it },
                label = { Text("Nazwa zadania:") },
                modifier = Modifier.fillMaxWidth()
            )

            OutlinedTextField(
                value = description,
                onValueChange = { description = it },
                label = { Text("Opis zadania:") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 8.dp),
                maxLines = 5
            )

            Spacer(modifier = Modifier.height(8.dp))

            PriorityDropdown(
                selectedPriority = priority,
                onPriorityChange = { priority = it }
            )

            Spacer(modifier = Modifier.height(8.dp))

            DeadlinePicker(
                selectedDate = deadline,
                onDateSelected = { deadline = it }
            )

            Spacer(modifier = Modifier.height(80.dp)) // zapas by nie nachodzić na przyciski
        }

        // Przyciski na dole — zawsze widoczne
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .systemBarsPadding(), // unika gestów dolnych
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CancelButton(onClick = onCancel)
            ConfirmButton {
                if (title.isNotBlank()) {
                    onConfirm(
                        Task(
                            title = title,
                            description = description,
                            priority = priority,
                            deadline = deadline
                        )
                    )
                }
            }
        }
    }
}
