package com.example.uicomponentstest.data.model.product.section

import com.example.uicomponentstest.data.model.product.section.content.SectionContentEntry

interface BaseSection {
    val sectionTitle: String
    val sectionContentEntry: List<SectionContentEntry>
}