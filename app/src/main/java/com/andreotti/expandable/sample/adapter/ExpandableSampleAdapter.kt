package com.andreotti.expandable.sample.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.andreotti.adapter.expandable.ExpandableAdapter
import com.andreotti.adapter.expandable.viewholder.ChildViewHolder
import com.andreotti.adapter.expandable.viewholder.GroupViewHolder
import com.andreotti.expandable.sample.R
import com.andreotti.expandable.sample.databinding.ItemChildSampleBinding
import com.andreotti.expandable.sample.databinding.ItemGroupSampleBinding
import com.andreotti.expandable.sample.model.SampleChild
import com.andreotti.expandable.sample.model.SampleGroup

internal class ExpandableSampleAdapter(items: List<SampleGroup>) :
    ExpandableAdapter<ExpandableSampleAdapter.GroupSampleViewHolder, ExpandableSampleAdapter.ChildSampleViewHolder>
        (items) {

    override fun onCreateGroupViewHolder(parent: ViewGroup, viewType: Int) = GroupSampleViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_group_sample, parent, false)
    )

    override fun onCreateChildViewHolder(parent: ViewGroup, viewType: Int) = ChildSampleViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_child_sample, parent, false)
    )

    override fun onBindGroupViewHolder(holder: GroupSampleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onBindChildViewHolder(holder: ChildSampleViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GroupSampleViewHolder(itemView: View) : GroupViewHolder(itemView) {
        private val binding = ItemGroupSampleBinding.bind(itemView)

        init {
            arrowView = binding.itemGroupArrow
        }

        fun bind(item: SampleGroup){
            binding.itemGroupText.text = item.title
        }
    }

    inner class ChildSampleViewHolder(itemView: View) : ChildViewHolder(itemView) {
        private val binding = ItemChildSampleBinding.bind(itemView)

        fun bind(item: SampleChild){
            binding.itemChildTitle.text = item.title
            binding.itemChildDescription.text = item.description
        }
    }
}