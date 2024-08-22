package com.example.uicomponentstest.data.model.product.section.content.important

import com.example.uicomponentstest.data.model.product.section.content.general.GeneralSectionEntry

data class ImportantSectionMultipleItemsEntry(
    override val title: String,
    val value: List<GeneralSectionEntry>,
) : ImportantEntry