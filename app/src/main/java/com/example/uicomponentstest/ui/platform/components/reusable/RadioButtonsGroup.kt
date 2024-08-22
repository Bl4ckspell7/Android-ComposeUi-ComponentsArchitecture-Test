package com.example.uicomponentstest.ui.platform.components.reusable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


/**
 * Template for a group of radio buttons with a text next to each button
 * Accepts generic types which implement the ISelectableOption interface
 *
 * @param selectableOptions a list of selectable options, where each option will have an associated radio button generated
 * @param trigger to pass the selected option to the outer control
 */
@Composable
fun <T : ISelectableOption> RadioButtonsGroup(
    selectableOptions: List<T>, preselectedOption: T, trigger: (T) -> Unit
) {
    var selectedOption by remember { mutableStateOf(preselectedOption) }
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .selectableGroup()
            .verticalScroll(state = scrollState, enabled = true, reverseScrolling = false)
    ) {
        selectableOptions.forEach { selectableOption ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(48.dp)
                    .selectable(selected = selectedOption == selectableOption, onClick = {
                        selectedOption = selectableOption
                        trigger(selectedOption)
                    }),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                RadioButton(
                    selected = selectedOption == selectableOption,
                    onClick = null,
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp)
                )
                Text(
                    text = selectableOption.displayName
                )
            }
        }
    }
}