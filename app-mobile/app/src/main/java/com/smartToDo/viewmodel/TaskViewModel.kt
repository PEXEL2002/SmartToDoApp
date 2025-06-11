package com.smartToDo.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.smartToDo.data.local.TaskEntity
import com.smartToDo.data.local.TaskDatabase
import com.smartToDo.data.repository.TaskRepository
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

enum class SortField { DEADLINE, PRIORITY, TITLE }
enum class SortDirection { ASCENDING, DESCENDING }

data class SortOption(val field: SortField, val direction: SortDirection)

sealed class PriorityFilter {
    object All : PriorityFilter()
    data class Only(val level: Int) : PriorityFilter()
    object SortAscending : PriorityFilter()
    object SortDescending : PriorityFilter()
}

class TaskViewModel(application: Application) : AndroidViewModel(application) {

    private val dao = TaskDatabase.getDatabase(application).taskDao()
    private val repository = TaskRepository(dao)

    private val _sortMode = MutableStateFlow(SortOption(SortField.DEADLINE, SortDirection.ASCENDING))
    val sortMode: StateFlow<SortOption> = _sortMode.asStateFlow()

    private val _priorityFilter = MutableStateFlow<PriorityFilter>(PriorityFilter.All)
    val priorityFilter: StateFlow<PriorityFilter> = _priorityFilter.asStateFlow()

    private val _tasks = repository.allTasks

    val sortedTasks: Flow<List<TaskEntity>> = combine(_tasks, _sortMode, _priorityFilter) { tasks, option, filter ->
        val base = tasks.filter { !it.isDone }

        val filtered = when (filter) {
            is PriorityFilter.All -> base
            is PriorityFilter.Only -> base.filter { it.priority == filter.level }
            is PriorityFilter.SortAscending -> base.sortedBy { it.priority }
            is PriorityFilter.SortDescending -> base.sortedByDescending { it.priority }
        }

        val sorted = when (option.field) {
            SortField.DEADLINE -> filtered.sortedBy { it.deadline ?: Long.MAX_VALUE }
            SortField.PRIORITY -> filtered
            SortField.TITLE -> filtered.sortedBy { it.title.lowercase() }
        }

        if (option.direction == SortDirection.DESCENDING && option.field != SortField.PRIORITY)
            sorted.reversed() else sorted
    }

    fun toggleSort(field: SortField) {
        _sortMode.update { current ->
            if (current.field == field) {
                val newDirection = if (current.direction == SortDirection.ASCENDING)
                    SortDirection.DESCENDING else SortDirection.ASCENDING
                current.copy(direction = newDirection)
            } else {
                SortOption(field, SortDirection.ASCENDING)
            }
        }
    }

    fun setPriorityFilter(filter: PriorityFilter) {
        _priorityFilter.value = filter
    }

    fun addTask(task: TaskEntity) = viewModelScope.launch {
        repository.insert(task)
    }

    fun updateTask(task: TaskEntity) = viewModelScope.launch {
        repository.update(task)
    }

    fun deleteTask(task: TaskEntity) = viewModelScope.launch {
        repository.delete(task)
    }
}