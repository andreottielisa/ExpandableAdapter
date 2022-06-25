package com.andreotti.adapter.expandable.accessibility

import android.view.View
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat

internal class AccessibilityDelegate : AccessibilityDelegateCompat() {

    private var className: CharSequence? = null
    private var action: AccessibilityActionCompat? = null

    override fun onInitializeAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfoCompat) {
        super.onInitializeAccessibilityNodeInfo(host, info)
        className?.let { info.className = it }
        action?.let { info.addAction(it) }
    }

    fun setClassName(className: CharSequence?) = apply {
        this.className = className
    }

    fun setAction(action: AccessibilityActionCompat?) = apply {
        this.action = action
    }

}