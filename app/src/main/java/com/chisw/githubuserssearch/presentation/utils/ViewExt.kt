package com.chisw.githubuserssearch.presentation.utils

import android.app.Activity
import android.content.res.ColorStateList
import android.view.MenuItem
import android.view.View
import androidx.constraintlayout.widget.Group
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

fun View.setGone() {
    if (visibility != View.GONE) {
        visibility = View.GONE
    }
}

fun View.setVisible() {
    isEnabled = true
    if (visibility != View.VISIBLE) {
        visibility = View.VISIBLE
    }
}

fun View.setInvisible() {
    isEnabled = false
    if (visibility != View.INVISIBLE) {
        visibility = View.INVISIBLE
    }
}

fun View.isVisible(): Boolean = visibility == View.VISIBLE

fun View.isGone(): Boolean = visibility == View.GONE

fun View.isInvisible(): Boolean = visibility == View.INVISIBLE

fun MenuItem.setGone() {
    if (isVisible) {
        isVisible = false
    }
}

fun MenuItem.setVisible() {
    isEnabled = true
    if (!isVisible) {
        isVisible = true
    }
}

fun View.show(show: Boolean) {
    if (show) setVisible() else setGone()
}

fun Group.setAllOnClickListener(listener: () -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnClickListener { listener.invoke() }
    }
}

fun Group.setAllOnLongClickListener(listener: () -> Unit) {
    referencedIds.forEach { id ->
        rootView.findViewById<View>(id).setOnLongClickListener {
            listener.invoke()
            true
        }
    }
}

fun Group.setAllOnClickListener(listener: View.OnClickListener?) =
    setAllOnClickListener { listener?.onClick(null) }

fun Group.setAllOnLongClickListener(listener: View.OnClickListener?) =
    setAllOnLongClickListener { listener?.onClick(null) }

/**
 * default setBackgroundTint doesnt work
 */
fun MaterialButton.setBackgroundTint(activity: Activity, colorResId: Int) {
    backgroundTintList = ContextCompat.getColorStateList(activity, colorResId)
}

fun MaterialButton.setStrokeColor(activity: Activity, colorResId: Int) {
    strokeColor = ColorStateList.valueOf(ContextCompat.getColor(activity, colorResId))
}