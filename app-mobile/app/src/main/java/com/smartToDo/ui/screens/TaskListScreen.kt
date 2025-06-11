package com.smartToDo.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.smartToDo.model.Task
import com.smartToDo.ui.components.AddButton
import com.smartToDo.ui.components.TaskCard
import com.smartToDo.viewmodel.*

@Composable
fun TaskListScreen(
    tasks: List<Task>,
    onDoneClick: (Task) -> Unit,
    onAddClick: () -> Unit,
    onEditClick: (Task) -> Unit,
    onToggleSort: (SortField) -> Unit,
    currentSort: SortOption,
    currentPriority: PriorityFilter,
    onPrioritySelect: (PriorityFilter) -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column {
            // Panel sortowania i filtrowania – zawsze widoczny
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .statusBarsPadding()
                    .padding(horizontal = 12.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                SortHeader("Tytuł", SortField.TITLE, currentSort, onToggleSort)
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    SortHeader("P", SortField.PRIORITY, currentSort, onToggleSort)
                    PriorityDropdownFilter(
                        current = currentPriority,
                        onSelect = onPrioritySelect
                    )
                }
                SortHeader("Deadline", SortField.DEADLINE, currentSort, onToggleSort)
            }

            if (tasks.isEmpty()) {
                val infoText = if (currentPriority == PriorityFilter.All) {
                    "Super, wszystkie zadania wykonane!"
                } else {
                    "Super, nie masz zadań o tym priorytecie!"
                }

                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(top = 32.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = infoText,
                        style = MaterialTheme.typography.bodyLarge
                    )
                }
            } else {
                LazyColumn {
                    items(tasks) { task ->
                        TaskCard(
                            task = task,
                            onDoneClick = onDoneClick,
                            modifier = Modifier
                                .padding(horizontal = 12.dp)
                                .clickable { onEditClick(task) }
                        )
                    }
                }
            }
        }

        AddButton(onClick = onAddClick)
    }
}

@Composable
fun SortHeader(
    title: String,
    field: SortField,
    currentSort: SortOption,
    onClick: (SortField) -> Unit
) {
    val arrow = when {
        currentSort.field == field && currentSort.direction == SortDirection.ASCENDING -> "▼"
        currentSort.field == field && currentSort.direction == SortDirection.DESCENDING -> "▲"
        else -> "▼"
    }

    Text(
        text = "$title $arrow",
        modifier = Modifier.clickable { onClick(field) }
    )
}

@Composable
fun PriorityDropdownFilter(
    current: PriorityFilter,
    onSelect: (PriorityFilter) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(modifier = Modifier.wrapContentSize()) {
        Text(
            text = when (current) {
                PriorityFilter.All -> "Wszystkie"
                is PriorityFilter.Only -> "P${current.level}"
                PriorityFilter.SortAscending -> "↑"
                PriorityFilter.SortDescending -> "↓"
            },
            modifier = Modifier
                .padding(top = 4.dp)
                .clickable { expanded = true }
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(text = { Text("Wszystkie") }, onClick = {
                onSelect(PriorityFilter.All)
                expanded = false
            })
            DropdownMenuItem(text = { Text("Sortuj rosnąco") }, onClick = {
                onSelect(PriorityFilter.SortAscending)
                expanded = false
            })
            DropdownMenuItem(text = { Text("Sortuj malejąco") }, onClick = {
                onSelect(PriorityFilter.SortDescending)
                expanded = false
            })
            HorizontalDivider()
            (0..5).forEach { p ->
                DropdownMenuItem(text = { Text("P$p") }, onClick = {
                    onSelect(PriorityFilter.Only(p))
                    expanded = false
                })
            }
        }
    }
}
