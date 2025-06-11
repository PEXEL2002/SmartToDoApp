package com.smartToDo.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tasks")
data class TaskEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val remoteId: String? = null,         // ID zdalny
    val title: String,
    val description: String?,
    val priority: Int,
    val deadline: Long?,                  // Timestamp (millis since epoch)
    val isDone: Boolean = false,

    val isSynced: Boolean = false,        // Czy zsynchronizowane z serwerem
    val updatedAt: Long = System.currentTimeMillis()  // Lokalny znacznik zmian
)
