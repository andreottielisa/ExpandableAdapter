package com.andreotti.adapter.expandable

interface ExpandableItem {
    var isExpanded: Boolean
    val items: List<Any>
}