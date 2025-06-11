package com.smartToDo.data.repository

import com.smartToDo.data.local.TaskDao
import com.smartToDo.data.local.TaskEntity
import kotlinx.coroutines.flow.Flow

class TaskRepository(private val taskDao: TaskDao) {

    val allTasks: Flow<List<TaskEntity>> = taskDao.getAllTasks()

    suspend fun insert(task: TaskEntity) {
        val updatedTask = task.copy(
            updatedAt = System.currentTimeMillis(),
            isSynced = false
        )
        taskDao.insertTask(updatedTask)
    }

    suspend fun update(task: TaskEntity) {
        val updatedTask = task.copy(
            updatedAt = System.currentTimeMillis(),
            isSynced = false
        )
        taskDao.updateTask(updatedTask)
    }

    suspend fun delete(task: TaskEntity) {
        taskDao.deleteTask(task)
    }

    suspend fun getUnsyncedTasks(): List<TaskEntity> {
        return taskDao.getUnsyncedTasks()
    }
}
