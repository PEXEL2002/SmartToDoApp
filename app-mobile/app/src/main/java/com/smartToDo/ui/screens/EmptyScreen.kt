package com.smartToDo.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun EmptyScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        OutlinedCard(
            border = ButtonDefaults.outlinedButtonBorder,
            modifier = Modifier.padding(32.dp)
        ) {
            Text(
                text = "Super, Wszystkie zadania już zrobione!",
                modifier = Modifier.padding(24.dp),
                style = MaterialTheme.typography.bodyLarge
            )
        }
    }
}
