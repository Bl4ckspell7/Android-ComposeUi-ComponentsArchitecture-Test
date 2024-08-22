package com.example.uicomponentstest.data.model.product

import android.net.Uri
import com.example.uicomponentstest.data.model.product.section.BaseSection
import java.util.Date

data class Product(
    val name: String,
    val type: ProductType,
    val imageUri: Uri,
    val lastUpdatedDate: Date,
    val sections: List<BaseSection>,
)