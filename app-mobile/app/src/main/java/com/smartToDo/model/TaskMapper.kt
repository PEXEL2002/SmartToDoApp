package com.smartToDo.model

import com.smartToDo.data.local.TaskEntity

fun Task.toEntity(): TaskEntity {
    return TaskEntity(
        id = id,
        remoteId = remoteId,
        title = title,
        description = description,
        priority = priority,
        deadline = deadline,
        isDone = isDone,
        isSynced = isSynced,
        updatedAt = updatedAt
    )
}

fun TaskEntity.toTask(): Task {
    return Task(
        id = id,
        remoteId = remoteId,
        title = title,
        description = description,
        priority = priority,
        deadline = deadline,
        isDone = isDone,
        isSynced = isSynced,
        updatedAt = updatedAt
    )
}
