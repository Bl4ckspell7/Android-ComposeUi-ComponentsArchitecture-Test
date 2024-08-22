package com.example.uicomponentstest.data.model.product.section

import com.example.uicomponentstest.data.model.product.section.content.SectionContentEntry
import com.example.uicomponentstest.data.model.product.section.content.important.ImportantEntry

class ImportantSection(private val importantEntries: List<ImportantEntry>) : BaseSection {
    override val sectionTitle: String
        get() = "Important Section"
    override val sectionContentEntry: List<SectionContentEntry>
        get() = importantEntries
}