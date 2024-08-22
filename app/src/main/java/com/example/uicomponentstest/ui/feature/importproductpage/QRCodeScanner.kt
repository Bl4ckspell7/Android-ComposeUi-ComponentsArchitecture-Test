package com.example.uicomponentstest.ui.feature.importproductpage

import android.content.Context
import com.example.uicomponentstest.ui.platform.util.toast
import com.google.mlkit.vision.codescanner.GmsBarcodeScannerOptions
import com.google.mlkit.vision.codescanner.GmsBarcodeScanning
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.cancellation.CancellationException
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

/**
 * A class for scanning QR codes using Google Mobile Services (GMS) Barcode Scanner.
 *
 * This class sets up a barcode scanner with options configured for QR code scanning.
 * It provides functionality to initiate a scan and retrieve the data from a scanned QR code.
 */
class QRCodeScanner {
    private val options = GmsBarcodeScannerOptions.Builder().enableAutoZoom().build()

    /**
     * This method initiates a QR code scan.
     *
     * The scanning process is handled asynchronously, and the result is returned as a [String].
     *
     * @param context The context from which the scan is initiated. This is usually an Activity or
     *                Application context.
     * @return A [String] containing the data encoded in the scanned QR code.
     * @throws CancellationException if the scanning process is cancelled.
     * @throws Exception if the scanning process fails due to an error.
     */
    suspend fun getData(context: Context): String =
        suspendCancellableCoroutine { continuation ->

            val scanner = GmsBarcodeScanning.getClient(context, options)

            scanner.startScan().addOnSuccessListener { barcode ->
                // Task completed successfully
                val result = barcode.rawValue.toString()
                context.toast("SUCCESS")
                continuation.resume(result)
            }.addOnCanceledListener {
                // Task canceled
                context.toast("CANCELLED")
                continuation.resumeWithException(CancellationException("Scan was cancelled"))
            }.addOnFailureListener {
                // Task failed with an exception
                context.toast("FAILED")
                continuation.resumeWithException(it)
            }
        }
}