package com.andreotti.adapter.expandable

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.andreotti.adapter.expandable.viewholder.ChildViewHolder
import com.andreotti.adapter.expandable.viewholder.GroupViewHolder

private const val GROUP = 0
private const val CHILD = 1

@Suppress("UNCHECKED_CAST")
abstract class ExpandableAdapter<GVH : GroupViewHolder, CVH : ChildViewHolder>(
    private val dataSet: List<ExpandableItem>
) : RecyclerView.Adapter<RecyclerView.ViewHolder>(), OnGroupClickListener {

    private val displayList = mutableListOf<Any>()

    init {
        setupDisplayList()
    }

    abstract fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int): GVH
    abstract fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int): CVH

    abstract fun onBindGroupViewHolder(holder: GVH, position: Int)
    abstract fun onBindChildViewHolder(holder: CVH, position: Int)

    protected fun <T> getItem(position: Int): T = displayList[position] as T

    override fun getItemCount(): Int = displayList.size

    override fun getItemViewType(position: Int): Int =
        if (getItem<Any>(position) is ExpandableItem) GROUP else CHILD

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        when (viewType) {
            GROUP -> onCreateGroupViewHolder(parent, viewType)
            CHILD -> onCreateChildViewHolder(parent, viewType)
            else -> throw IllegalArgumentException("ViewType is not valid")
        }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) =
        when (holder) {
            is GroupViewHolder -> {
                onBindGroupViewHolder(holder as GVH, position)
                holder.onBindViewHolder(this, isAttachedChildren(position))
            }
            is ChildViewHolder -> onBindChildViewHolder(holder as CVH, position)
            else -> throw IllegalArgumentException("ViewHolder is not valid")
        }

    private fun setupDisplayList() {
        displayList.clear()

        for (it in dataSet) {
            displayList.add(it)

            val children = it.items

            if (it.isExpanded && children.isNotEmpty()) {
                displayList.addAll(children)
            }
        }
    }

    override fun notifyUpdateGroup(index: Int) {
        val group = displayList[index] as? ExpandableItem
        val children: List<Any>? = group?.items

        if (group == null || children.isNullOrEmpty()) {
            return
        }

        if (group.isExpanded) {
            removeGroup(index, children)
        } else {
            addGroup(index, children)
        }

        group.isExpanded = !group.isExpanded
    }

    private fun removeGroup(index: Int, children: List<Any>) {
        val itemIndex = index.plus(1)
        displayList.removeAll(itemIndex, children)
        notifyItemRangeRemoved(itemIndex, children.size)
    }

    private fun addGroup(index: Int, children: List<Any>) {
        val displayIndex = index.plus(1)
        displayList.addAll(displayIndex, children)
        notifyItemRangeInserted(displayIndex, children.size)
    }

    private fun isAttachedChildren(position: Int): Boolean {
        val group = displayList[position] as? ExpandableItem
        val children: List<Any>? = group?.items
        return group != null && !children.isNullOrEmpty() && group.isExpanded
    }

    private fun MutableList<Any>.removeAll(index: Int, elements: List<Any>) {
        for (i in elements.indices) {
            removeAt(index)
        }
    }
}