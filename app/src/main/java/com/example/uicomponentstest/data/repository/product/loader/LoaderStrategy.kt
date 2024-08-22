package com.example.uicomponentstest.data.repository.product.loader

import com.example.uicomponentstest.data.model.product.Product

interface LoaderStrategy {
    fun load(): Product
}