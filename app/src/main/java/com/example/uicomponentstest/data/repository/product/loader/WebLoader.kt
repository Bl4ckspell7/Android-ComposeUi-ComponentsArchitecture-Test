package com.example.uicomponentstest.data.repository.product.loader

import com.example.uicomponentstest.data.model.product.ExampleProduct
import com.example.uicomponentstest.data.model.product.Product

class WebLoader(private val webAddress: String) : LoaderStrategy {
    override fun load(): Product {
        // TODO
        // connect to server
        // retrieve all product data, including image (-> save it to phone storage)
        // return product
        return ExampleProduct().getExampleProduct(name = webAddress)
    }
}