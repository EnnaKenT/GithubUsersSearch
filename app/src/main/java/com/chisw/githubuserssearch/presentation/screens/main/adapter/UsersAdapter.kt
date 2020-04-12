package com.chisw.githubuserssearch.presentation.screens.main.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.chisw.githubuserssearch.R
import com.chisw.githubuserssearch.data.model.Item
import com.chisw.githubuserssearch.presentation.screens.base.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.item_user_rv.view.*

class UsersAdapter(
    private val requestNextPageAction: () -> Unit,
    private val itemClickedAction: (Item) -> Unit
) : RecyclerViewAdapter<Item, UsersAdapter.TaskViewHolder>(DiffUtilItemCallback()) {

    var totalItemsCount = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder =
        TaskViewHolder(inflate(parent, R.layout.item_user_rv))

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
        if (position + USERS_OFFSET > itemCount && totalItemsCount != itemCount)
            requestNextPageAction.invoke()
    }

    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        init {
            itemView.setOnClickListener { itemClickedAction(getItem(adapterPosition)) }
        }

        fun bind(item: Item) =
            with(itemView) {
                loginTv.text = item.login
                avatarIv.load(item.avatar_url)
            }
    }

    companion object {
        private const val USERS_OFFSET = 10
    }

}

private class DiffUtilItemCallback : DiffUtil.ItemCallback<Item>() {
    override fun areItemsTheSame(oldItem: Item, newItem: Item) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Item, newItem: Item) = oldItem == newItem
}