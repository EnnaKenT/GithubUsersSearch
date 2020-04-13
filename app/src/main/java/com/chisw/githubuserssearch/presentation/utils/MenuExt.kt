package com.chisw.githubuserssearch.presentation.utils

import android.app.Activity
import android.view.MenuItem

fun MenuItem.setBottomBarExpandListener(activity: Activity) {
    setOnActionExpandListener(object : MenuItem.OnActionExpandListener {
        override fun onMenuItemActionExpand(item: MenuItem?): Boolean = true

        override fun onMenuItemActionCollapse(item: MenuItem?): Boolean {
            activity.hideKeyboard()
            return true
        }
    })
}