package com.example.uicomponentstest.ui.platform.components.reusable

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import com.example.uicomponentstest.R


/**
 * Template for a Dialog which gets opened by a Button
 *
 * This composable function displays a button with the given text, and when clicked,
 * it opens a dialog with a title and custom content. The dialog includes an "OK" button
 * that triggers a specified action when clicked.
 *
 * @param buttonText the text of the button
 * @param dialogTitle the dialog title
 * @param dialogContent the @Composable inside the dialog
 * @param onConfirmClick the function which gets called when the "OK" button of the dialog gets clicked
 */
@Composable
fun TestOpenDialogWithButton(
    buttonText: String,
    dialogTitle: String,
    dialogContent: @Composable () -> Unit,
    onConfirmClick: () -> Unit
) {
    val openAlertDialog = remember { mutableStateOf(false) }

    /**
     * Button which triggers the dialog to open
     */
    Button(onClick = { openAlertDialog.value = true }) {
        Text(text = buttonText)
    }

    /**
     * Check if the alert dialog should be open
     */
    if (openAlertDialog.value) {
        DialogPopup(
            dialogTitle = dialogTitle,
            dialogContent = dialogContent,
            onDismissRequest = { openAlertDialog.value = false },
            onConfirmation = {
                openAlertDialog.value = false
                onConfirmClick()
            },
        )
    }
}

/**
 * A reusable Dialog Popup.
 *
 * This composable function displays a dialog with a title, custom content, and "OK" and "Cancel" buttons.
 * It handles the logic for dismissing the dialog and confirming an action.
 *
 * @param onDismissRequest A function that gets called when the dialog is dismissed or the "Cancel" button is clicked.
 * @param onConfirmation A function that gets called when the "OK" button in the dialog is clicked.
 * @param dialogTitle The title displayed at the top of the dialog.
 * @param dialogContent A composable function that defines the custom content inside the dialog.
 */
@Composable
fun DialogPopup(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogContent: @Composable () -> Unit,
) {
    AlertDialog(title = {
        Text(text = dialogTitle)
    }, text = {
        // text parameter is actually a @Composable, so we can modify it to display anything
        dialogContent()
    }, onDismissRequest = {
        onDismissRequest()
    }, confirmButton = {
        TextButton(onClick = {
            onConfirmation()
        }) {
            Text(stringResource(R.string.ok))
        }
    }, dismissButton = {
        TextButton(onClick = {
            onDismissRequest()
        }) {
            Text(stringResource(R.string.cancel))
        }
    })
}