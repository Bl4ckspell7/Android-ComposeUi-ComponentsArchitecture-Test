package com.example.uicomponentstest.data.model.product.section.content.submodel

/**
 *
 * Composite
 *
 */
data class SubmodelGroup(
    val submodelGroupIdShort: String,
    val submodelGroupDisplayName: String,
    val submodelGroupChildren: ArrayList<ISubmodelEntry>,
) : ISubmodelEntry {

    fun addChild(iSubmodelEntry: ISubmodelEntry) {
        submodelGroupChildren.add(iSubmodelEntry)
    }

    fun removeChild(iSubmodelEntry: ISubmodelEntry) {
        submodelGroupChildren.remove(iSubmodelEntry)
    }

    fun getChildren(): List<ISubmodelEntry> {
        return submodelGroupChildren
    }
}