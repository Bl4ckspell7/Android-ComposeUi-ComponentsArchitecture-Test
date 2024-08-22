package com.example.uicomponentstest.ui.platform.util

import android.content.Context
import android.widget.Toast

/**
 * Shows a toast message with the given text.
 *
 * @param message
 */
fun Context.toast(message: CharSequence) = Toast.makeText(this, message, Toast.LENGTH_SHORT).show()