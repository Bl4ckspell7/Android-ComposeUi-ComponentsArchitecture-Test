package com.example.uicomponentstest.ui.platform.components.specific

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BottomSheetExample() {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    Scaffold(floatingActionButton = {
        ExtendedFloatingActionButton(text = { Text("Show bottom sheet") },
            icon = { Icon(Icons.Filled.Add, contentDescription = null) },
            onClick = {
                showBottomSheet = true
            })
    }) { contentPadding ->
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                }, sheetState = sheetState
            ) {
                // Sheet content
                Column(
                    modifier = Modifier.padding(contentPadding),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ListItem(headlineContent = { Text("Three line list item") },
                        overlineContent = { Text("OVERLINE") },
                        supportingContent = { Text("Secondary text") },
                        leadingContent = {
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Localized description",
                            )
                        },
                        trailingContent = { Text("meta") })
                    HorizontalDivider()
                    Text("text")
                    HorizontalDivider()
                    Button(onClick = {
                        scope.launch { sheetState.hide() }.invokeOnCompletion {
                            if (!sheetState.isVisible) {
                                showBottomSheet = false
                            }
                        }
                    }) {
                        Text("Hide bottom sheet")
                    }
                }
            }
        }
    }
}