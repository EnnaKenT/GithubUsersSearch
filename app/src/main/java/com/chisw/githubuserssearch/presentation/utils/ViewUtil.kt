package com.chisw.githubuserssearch.presentation.utils

import android.app.Activity
import android.content.res.ColorStateList
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

fun View.setInvisible() {
    isEnabled = false
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

fun View.setInvisibleEnable() {
    isEnabled = true
    if (visibility != View.INVISIBLE) visibility = View.INVISIBLE
}

fun MenuItem.setGone() {
    if (isVisible) isVisible = false
}

fun MenuItem.setVisible() {
    isEnabled = true
    if (!isVisible) isVisible = true
}

fun View.show(show: Boolean) {
    val visibility =
        if (show) View.VISIBLE
        else View.GONE
    if (this.visibility != visibility) this.visibility = visibility
}

fun View.showWithInvisible(show: Boolean) {
    val visibility =
        if (show) View.VISIBLE
        else View.INVISIBLE
    if (this.visibility != visibility) this.visibility = visibility
}

fun EditText.clear() = editableText.clear()

/**
 * default setBackgroundTint doesnt work
 */
fun MaterialButton.setBackgroundTint(activity: Activity, colorResId: Int) {
    backgroundTintList = ContextCompat.getColorStateList(activity, colorResId)
}

fun MaterialButton.setStrokeColor(activity: Activity, colorResId: Int) {
    strokeColor = ColorStateList.valueOf(ContextCompat.getColor(activity, colorResId))
}

fun log(tag: String? = "duck", text: String) = Log.i(tag, text)