package com.andreotti.expandable.sample.factory

import android.content.res.Resources
import com.andreotti.expandable.sample.R
import com.andreotti.expandable.sample.model.SampleChild
import com.andreotti.expandable.sample.model.SampleGroup
import kotlin.random.Random

private const val FIRST_POSITION = 1
private const val AMOUNT_MAX = 4

internal class SampleModelFactory(private val resources: Resources) {

    fun create(): List<SampleGroup> {
        val items = arrayListOf<SampleGroup>()

        for (i in FIRST_POSITION..AMOUNT_MAX) {
            val groupItems = arrayListOf<SampleChild>()
            val amount = Random.nextInt(FIRST_POSITION, AMOUNT_MAX)

            for (x in FIRST_POSITION..amount) {
                groupItems.add(createChild(x))
            }

            items.add(createGroup(i, groupItems))
        }

        return items
    }

    private fun createChild(position: Int) = SampleChild(
        title = resources.getString(R.string.item_child_title, position),
        description = resources.getString(R.string.item_child_description, position)
    )

    private fun createGroup(position: Int, items: List<SampleChild>) = SampleGroup(
        title = resources.getString(R.string.item_group, position),
        items = items,
        isExpanded = position == FIRST_POSITION
    )
}