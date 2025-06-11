package com.smartToDo.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ConfirmButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFF228B22),
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Check,
            contentDescription = "Zatwierdź",
            tint = Color.White
        )
    }
}

@Composable
fun CancelButton(onClick: () -> Unit) {
    FloatingActionButton(
        onClick = onClick,
        containerColor = Color(0xFF228B22),
        shape = CircleShape
    ) {
        Icon(
            imageVector = Icons.Default.Close,
            contentDescription = "Anuluj",
            tint = Color.White
        )
    }
}

@Composable
fun AddButton(onClick: () -> Unit) {
    Box(
        contentAlignment = Alignment.BottomEnd,
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        FloatingActionButton(
            onClick = onClick,
            containerColor = Color(0xFF228B22),
            shape = CircleShape
        ) {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Dodaj",
                tint = Color.White
            )
        }
    }
}
