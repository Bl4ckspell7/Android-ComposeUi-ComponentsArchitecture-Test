package com.example.uicomponentstest.data.repository.product

import android.util.Log
import com.example.uicomponentstest.data.model.product.Product
import com.example.uicomponentstest.data.repository.product.loader.LoaderStrategy
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ProductRepositoryImpl : ProductRepository {
    private lateinit var loaderStrategy: LoaderStrategy

    private val _product = MutableStateFlow<Product?>(null)
    override val product: StateFlow<Product?> = _product

    override fun setLoader(loaderStrategy: LoaderStrategy) {
        this.loaderStrategy = loaderStrategy
    }

    override fun loadProductIntoRepository() {
        if (!::loaderStrategy.isInitialized) {
            throw IllegalStateException("LoaderStrategy is not set")
        }
        _product.value = loaderStrategy.load()
    }
}