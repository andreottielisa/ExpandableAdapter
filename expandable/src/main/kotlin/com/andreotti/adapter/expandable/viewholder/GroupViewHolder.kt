package com.andreotti.adapter.expandable.viewholder

import android.view.View
import android.widget.Button
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_COLLAPSE
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_EXPAND
import androidx.recyclerview.widget.RecyclerView
import com.andreotti.adapter.expandable.OnGroupClickListener
import com.andreotti.adapter.expandable.R
import com.andreotti.adapter.expandable.accessibility.setAccessibility
import com.andreotti.adapter.expandable.accessibility.setContentDescription

private const val ICON_ARROW_DOWN_ROTATION = 0f
private const val ICON_ARROW_UP_ROTATION = 180f
private const val ICON_ARROW_ANIMATION_DURATION = 200L

abstract class GroupViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    protected var arrowView: View? = null
    private var groupClickListener: OnGroupClickListener? = null
        set(value) {
            field = value
            field ?: return
            itemView.setOnClickListener {
                isExpanded = !isExpanded
                value?.notifyUpdateGroup(absoluteAdapterPosition)
            }
        }

    private var isExpanded: Boolean = false
        set(value) {
            field = value
            if (value) {
                expand()
            } else {
                collapse()
            }
        }

    open fun expand(animate: Boolean = true) {
        itemView.setAccessibility(action = ACTION_COLLAPSE, className = Button::class)
        arrowView?.let {
            it.setContentDescription(R.string.accessibility_expand)
            it.rotate(ICON_ARROW_UP_ROTATION, animate)
        }
    }

    open fun collapse(animate: Boolean = true) {
        itemView.setAccessibility(action = ACTION_EXPAND, className = Button::class)
        arrowView?.let {
            it.setContentDescription(R.string.accessibility_collapse)
            it.rotate(ICON_ARROW_DOWN_ROTATION, animate)
        }
    }

    internal fun onBindViewHolder(listener: OnGroupClickListener?, isExpanded: Boolean) {
        groupClickListener = listener
        if (isExpanded) expand(false) else collapse(false)
        this.isExpanded = isExpanded
    }

    private fun View.rotate(value: Float, animate: Boolean) {
        if (rotation == value) return

        if (animate) {
            animate().rotation(value).duration = ICON_ARROW_ANIMATION_DURATION
        } else {
            rotation = value
        }
    }
}