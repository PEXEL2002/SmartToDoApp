package com.smartToDo.ui.components

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalConfiguration

@Composable
fun ResponsiveContainer(
    portrait: @Composable () -> Unit,
    landscape: @Composable () -> Unit
) {
    val config = LocalConfiguration.current
    if (config.orientation == Configuration.ORIENTATION_LANDSCAPE) {
        landscape()
    } else {
        portrait()
    }
}
