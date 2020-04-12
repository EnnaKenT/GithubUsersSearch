package com.chisw.githubuserssearch.presentation.utils

import android.app.Activity
import android.content.Context
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.presentation.screens.base.activity.BaseActivity

/**
 * for base fragment logic
 */
fun Activity.showToast(message: Failure<*>) {
    if (this is BaseActivity) this.showToast(message)
}

fun BaseActivity.toast(message: Failure<*>) =
    Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()

fun Activity.hideKeyboard() {
    val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    //Find the currently focused view, so we can grab the correct window token from it.
    var view = currentFocus
    //If no view currently has focus, create a new one, just so we can grab a window token from it
    if (view == null) {
        view = View(this)
    }
    imm.hideSoftInputFromWindow(view.windowToken, 0)
}

fun Activity.showKeyboard(view: View) {
    val keyboard = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    keyboard.showSoftInput(view, 0)
}

fun MenuItem.setBottomBarExpandListener(activity: Activity) {
    setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem?): Boolean = true

        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            activity.hideKeyboard()
            return true
        }
    })
}