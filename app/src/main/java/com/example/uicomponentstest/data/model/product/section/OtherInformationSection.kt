package com.example.uicomponentstest.data.model.product.section

import com.example.uicomponentstest.data.model.product.section.content.SectionContentEntry
import com.example.uicomponentstest.data.model.product.section.content.submodel.ISubmodelEntry

class OtherInformationSection(private val submodelEntry: ISubmodelEntry) : BaseSection {
    override val sectionTitle: String
        get() = "Other Information"
    override val sectionContentEntry: List<SectionContentEntry>
        // ISubmodelEntry is a composite itself
        get() = listOf(submodelEntry)
}