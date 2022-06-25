package com.andreotti.adapter.expandable.accessibility

import android.view.View
import androidx.annotation.StringRes
import androidx.core.view.ViewCompat.setAccessibilityDelegate
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import kotlin.reflect.KClass

internal fun View.setAccessibility(
    className: KClass<*>? = null,
    action: AccessibilityNodeInfoCompat.AccessibilityActionCompat? = null
) {
    AccessibilityDelegate()
        .setClassName(className?.java?.name)
        .setAction(action)
        .also { setAccessibilityDelegate(this, it) }
}

internal fun View.setContentDescription(@StringRes res: Int) {
    contentDescription = resources.getString(res)
}