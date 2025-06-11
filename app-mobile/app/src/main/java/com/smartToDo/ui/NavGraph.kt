package com.smartToDo.ui

import androidx.compose.runtime.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.smartToDo.model.toEntity
import com.smartToDo.model.toTask
import com.smartToDo.viewmodel.*
import com.smartToDo.ui.screens.*

sealed class Screen(val route: String) {
    object TaskList : Screen("task_list")
    object AddTask : Screen("add_task")
    object EditTask : Screen("edit_task")
}

@Composable
fun NavGraph(viewModel: TaskViewModel) {
    val navController = rememberNavController()
    var selectedTask by remember { mutableStateOf<com.smartToDo.model.Task?>(null) }

    val sortedTasks by viewModel.sortedTasks.collectAsState(initial = emptyList())
    val sortOption by viewModel.sortMode.collectAsState()
    val priorityFilter by viewModel.priorityFilter.collectAsState()

    NavHost(navController = navController, startDestination = Screen.TaskList.route) {
        composable(Screen.TaskList.route) {
            TaskListScreen(
                tasks = sortedTasks.map { it.toTask() },
                onDoneClick = { task -> viewModel.updateTask(task.copy(isDone = true).toEntity()) },
                onAddClick = { navController.navigate(Screen.AddTask.route) },
                onEditClick = { task ->
                    selectedTask = task
                    navController.navigate(Screen.EditTask.route)
                },
                onToggleSort = { viewModel.toggleSort(it) },
                currentSort = sortOption,
                currentPriority = priorityFilter,
                onPrioritySelect = { viewModel.setPriorityFilter(it) }
            )
        }

        composable(Screen.AddTask.route) {
            AddTaskScreen(
                onConfirm = { task ->
                    viewModel.addTask(task.toEntity())
                    navController.popBackStack()
                },
                onCancel = { navController.popBackStack() }
            )
        }

        composable(Screen.EditTask.route) {
            selectedTask?.let { task ->
                EditTaskScreen(
                    task = task,
                    onConfirm = {
                        viewModel.updateTask(it.toEntity())
                        navController.popBackStack()
                    },
                    onCancel = { navController.popBackStack() }
                )
            }
        }
    }
}
