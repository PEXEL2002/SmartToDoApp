package com.smartToDo.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PriorityDropdown(
    selectedPriority: Int,
    onPriorityChange: (Int) -> Unit
) {
    val priorities = (0..5).toList()
    var expanded by remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = expanded,
        onExpandedChange = { expanded = !expanded }
    ) {
        TextField(
            value = "P$selectedPriority",
            onValueChange = {},
            readOnly = true,
            label = { Text("Priorytet") },
            modifier = Modifier
                .fillMaxWidth()
                .menuAnchor()
        )

        ExposedDropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            priorities.forEach { priority ->
                DropdownMenuItem(
                    text = { Text("P$priority") },
                    onClick = {
                        onPriorityChange(priority)
                        expanded = false
                    }
                )
            }
        }
    }
}
