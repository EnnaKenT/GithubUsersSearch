package com.chisw.githubuserssearch.presentation.screens.utils

import android.content.Context
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.addItemDecoration(context: Context) =
    addItemDecoration(DividerItemDecoration(context, LinearLayoutManager(context).orientation))