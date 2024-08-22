package com.example.uicomponentstest.ui.feature.importproductpage

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.uicomponentstest.data.repository.product.ProductRepository
import com.example.uicomponentstest.data.repository.product.loader.DatabaseLoader
import com.example.uicomponentstest.data.repository.product.loader.WebLoader
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel for managing product import operations from various sources.
 *
 * This ViewModel loads product data into a [ProductRepository]
 * from different sources such as web and database, and also facilitates
 * QR code scanning to fetch product information.
 *
 * @property productRepository The repository where product data is loaded and stored.
 * @constructor Creates an instance of [ImportProductPageViewModel] with the specified [ProductRepository].
 */
@HiltViewModel
class ImportProductPageViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    // A private mutable state flow to hold the QR code scan result.
    private var _qrCodeScanResult = MutableStateFlow("scan result")

    // A public read-only state flow exposing the QR code scan result.
    val qrCodeScanResult = _qrCodeScanResult.asStateFlow()


    /**
     * Loads product data from a web source into the repository.
     * Sets the loader to a [WebLoader] and loads the product using the provided URL string.
     *
     * @param string The URL string from which the product data is to be loaded.
     */
    fun loadFromWeb(string: String) {
        productRepository.setLoader(WebLoader(string))
        productRepository.loadProductIntoRepository()
    }

    /**
     * Loads product data from a database into the repository.
     * Sets the loader to a [DatabaseLoader] and loads the product using the provided query string.
     *
     * @param string The database query string used to fetch product data.
     */
    fun loadFromDatabase(string: String) {
        productRepository.setLoader(DatabaseLoader(string))
        productRepository.loadProductIntoRepository()
    }

    /**
     * Initiates a QR code scan operation.
     * Utilizes a [QRCodeScanner] instance to perform QR code scanning and updates the scan result.
     *
     * @param context The [Context] used for initializing the QR code scanner.
     * @param coroutineScope The [CoroutineScope] used for launching the asynchronous scan operation.
     */
    fun startScan(context: Context, coroutineScope: CoroutineScope) {
        coroutineScope.launch {
            try {
                _qrCodeScanResult.value = QRCodeScanner().getData(context)
            } catch (e: Exception) {
                println(e.message)
                _qrCodeScanResult.value = "Error: ${e.message}"
            }
        }
    }
}