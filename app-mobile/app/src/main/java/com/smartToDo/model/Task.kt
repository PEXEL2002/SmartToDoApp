package com.smartToDo.model

data class Task(
    val id: Int = 0,
    val remoteId: String? = null,
    val title: String,
    val description: String?,
    val priority: Int,
    val deadline: Long?,      // Timestamp
    val isDone: Boolean = false,
    val isSynced: Boolean = false,
    val updatedAt: Long = System.currentTimeMillis()
)
