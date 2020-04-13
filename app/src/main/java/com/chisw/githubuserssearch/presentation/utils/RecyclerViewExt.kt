package com.chisw.githubuserssearch.presentation.utils

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * for LinearLayoutManager
 */
fun RecyclerView.addDividerItemDecoration(context: Context) =
    addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(context).orientation))