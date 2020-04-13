package com.chisw.githubuserssearch.presentation.screen.base.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView

abstract class RecyclerViewAdapter<T, VH : RecyclerView.ViewHolder>(diffUtil: DiffUtil.ItemCallback<T>) :
    RecyclerView.Adapter<VH>() {

    private val differ = AsyncListDiffer(this, diffUtil)

    protected fun inflate(parent: ViewGroup, @LayoutRes layoutResId: Int): View {
        val inflater = LayoutInflater.from(parent.context)
        return inflater.inflate(layoutResId, parent, false)
    }

    fun setItems(mutableList: List<T>) {
        differ.submitList(mutableList)
        notifyDataSetChanged()
    }

    fun getItem(position: Int): T = differ.currentList[position]

    protected fun getItems(): List<T> = differ.currentList

    override fun getItemCount() = differ.currentList.size
}
