package com.example.uicomponentstest.data.model.product.section

import com.example.uicomponentstest.data.model.product.section.content.SectionContentEntry
import com.example.uicomponentstest.data.model.product.section.content.general.GeneralSectionEntry

class GeneralSection(private val generalSectionEntries: List<GeneralSectionEntry>) : BaseSection {
    override val sectionTitle: String
        get() = "General Section"
    override val sectionContentEntry: List<SectionContentEntry>
        get() = generalSectionEntries
}