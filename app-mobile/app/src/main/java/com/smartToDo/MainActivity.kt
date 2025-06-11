package com.smartToDo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.viewmodel.compose.viewModel
import com.smartToDo.ui.NavGraph
import com.smartToDo.ui.theme.SmartToDoTheme
import com.smartToDo.viewmodel.TaskViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SmartToDoTheme {
                val viewModel: TaskViewModel = viewModel()
                NavGraph(viewModel = viewModel)
            }
        }
    }
}
