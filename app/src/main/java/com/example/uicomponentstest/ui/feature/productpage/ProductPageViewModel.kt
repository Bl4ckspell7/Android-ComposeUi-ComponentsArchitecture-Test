package com.example.uicomponentstest.ui.feature.productpage

import androidx.lifecycle.ViewModel
import com.example.uicomponentstest.data.model.product.Product
import com.example.uicomponentstest.data.repository.product.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

/**
 * ViewModel for managing and filtering product data on a product page.
 *
 * This ViewModel interacts with a [ProductRepository] to retrieve and
 * filter product data for display.
 *
 * @property productRepository The repository used to fetch and store product data.
 * @constructor Creates a [ProductPageViewModel] with the specified [ProductRepository].
 */
@HiltViewModel
class ProductPageViewModel @Inject constructor(private val productRepository: ProductRepository) :
    ViewModel() {

    val product: StateFlow<Product?> = productRepository.product

    private var _filteredProduct = MutableStateFlow<Product?>(null)
    val filteredProduct: StateFlow<Product?> = _filteredProduct.asStateFlow()

    fun applyFilter() {
        val productFilter = ProductFilter()
        _filteredProduct.value = product.value?.let { productFilter.apply(it) }
    }
}

