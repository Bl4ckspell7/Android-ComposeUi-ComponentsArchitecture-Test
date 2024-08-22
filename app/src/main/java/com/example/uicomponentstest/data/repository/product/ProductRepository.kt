package com.example.uicomponentstest.data.repository.product

import com.example.uicomponentstest.data.model.product.Product
import com.example.uicomponentstest.data.repository.product.loader.LoaderStrategy
import kotlinx.coroutines.flow.StateFlow

interface ProductRepository {
    val product: StateFlow<Product?>
    fun loadProductIntoRepository()
    fun setLoader(loaderStrategy: LoaderStrategy)
}