package com.chisw.githubuserssearch.presentation.screens.utils

import android.content.Context
import com.chisw.githubuserssearch.R
import com.chisw.githubuserssearch.data.model.Hit
import com.chisw.githubuserssearch.presentation.screens.main.adapter.PostsAdapter
import java.text.SimpleDateFormat
import java.util.*

fun Hit.getCreatedAtTime(context: Context): String {
    val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())
    val formatter = SimpleDateFormat("dd.MM.yyyy HH:mm", Locale.getDefault())

    return context.getString(
        R.string.created_at_format,
        formatter.format(parser.parse(created_at)!!)
    )
}

fun Context.getSelectedItemsCount(adapter: PostsAdapter): String {
    val selectedItemsCount = adapter.getSelectedItems().size
    return if (selectedItemsCount == 0) getString(R.string.no_items_selected)
    else String.format(
        resources.getQuantityString(R.plurals.items_selected_format, selectedItemsCount),
        selectedItemsCount
    )
}