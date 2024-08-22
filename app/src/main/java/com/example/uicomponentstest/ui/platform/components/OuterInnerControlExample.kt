package com.example.uicomponentstest.ui.platform.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue

/**
 *
 * Communicate between Composables & avoid Anti-Pattern
 *
 * https://medium.com/mobile-app-development-publication/how-kotlin-by-variable-delegate-helps-me-avoid-anti-pattern-558004000341
 *
 */
@Composable
fun MyOuterView() {
    var timerStartStop by remember { mutableStateOf(true) }
    Column {
        MyInnerControl { timerStartStop = it }
        Text(if (timerStartStop) "Stop" else "Start")
    }
}

@Composable
fun MyInnerControl(trigger: (Boolean) -> Unit) {
    var startStop by remember { mutableStateOf(true) }
    Button(onClick = {
        startStop = !startStop
    }) {
        Text(if (startStop) "Stop" else "Start")
        trigger(startStop)
    }
}