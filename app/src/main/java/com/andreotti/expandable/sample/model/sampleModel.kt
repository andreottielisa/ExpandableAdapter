package com.andreotti.expandable.sample.model

import com.andreotti.adapter.expandable.ExpandableItem

internal data class SampleGroup(
    val title: String,
    override var isExpanded: Boolean,
    override val items: List<SampleChild>
) : ExpandableItem

internal data class SampleChild(
    val title: String,
    val description: String
)