package com.example.uicomponentstest.data.model.product

import android.net.Uri
import com.example.uicomponentstest.data.model.product.section.GeneralSection
import com.example.uicomponentstest.data.model.product.section.ImportantSection
import com.example.uicomponentstest.data.model.product.section.content.general.GeneralSectionEntry
import com.example.uicomponentstest.data.model.product.section.content.important.ImportantSectionMultipleItemsEntry
import com.example.uicomponentstest.data.model.product.section.content.important.ImportantSectionSingleItemEntry
import java.util.Date

class ExampleProduct {
    fun getExampleProduct(name: String): Product = Product(
        name = "Product Name: $name",
        imageUri = Uri.EMPTY,
        type = ProductType.OTHER,
        sections = listOf(
            GeneralSection(listOf(GeneralSectionEntry(key = "display", value = "asdasd"))),
            ImportantSection(
                listOf(
                    ImportantSectionSingleItemEntry(title = "single", value = "value"),
                    ImportantSectionMultipleItemsEntry(
                        title = "multiple", value = listOf(
                            GeneralSectionEntry(key = "display", value = "asdasd")
                        )
                    )
                )
            )
        ),
        lastUpdatedDate = Date()
    )
}