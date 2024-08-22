package com.example.uicomponentstest.data.repository.product.loader

import com.example.uicomponentstest.data.model.product.ExampleProduct
import com.example.uicomponentstest.data.model.product.Product

class DatabaseLoader(private val dbAddress: String) : LoaderStrategy {
    override fun load(): Product {
        return ExampleProduct().getExampleProduct(name = dbAddress)
    }
}