package com.smartToDo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.systemBarsPadding
import com.smartToDo.model.Task
import com.smartToDo.ui.components.CancelButton
import com.smartToDo.ui.components.ConfirmButton
import com.smartToDo.ui.components.DeadlinePicker
import com.smartToDo.ui.components.PriorityDropdown

@Composable
fun EditTaskScreen(
    task: Task,
    onConfirm: (Task) -> Unit,
    onCancel: () -> Unit
) {
    var title by remember { mutableStateOf(task.title) }
    var description by remember { mutableStateOf(task.description ?: "") }
    var priority by remember { mutableStateOf(task.priority) }
    var deadline by remember { mutableStateOf(task.deadline) }

    val scrollState = rememberScrollState()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(WindowInsets.systemBars.asPaddingValues())
    ) {
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

            Spacer(modifier = Modifier.height(80.dp)) // zapas nad przyciskami
        }

        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .padding(16.dp)
                .systemBarsPadding(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            CancelButton(onClick = onCancel)
            ConfirmButton {
                if (title.isNotBlank()) {
                    onConfirm(
                        Task(
                            id = task.id,
                            remoteId = task.remoteId,
                            title = title,
                            description = description,
                            priority = priority,
                            deadline = deadline,
                            isDone = task.isDone,
                            isSynced = false,
                            updatedAt = System.currentTimeMillis()
                        )
                    )
                }
            }
        }
    }
}
