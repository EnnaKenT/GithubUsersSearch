package com.chisw.githubuserssearch.presentation.screen.user_search.adapter

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.chisw.githubuserssearch.R
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.presentation.screen.base.adapter.RecyclerViewAdapter
import kotlinx.android.synthetic.main.item_user_rv.view.*

class UsersAdapter(
    private val requestNextPageAction: () -> Unit,
    private val itemClickedAction: (User) -> Unit
) : RecyclerViewAdapter<User, UsersAdapter.TaskViewHolder>(DiffUtilItemCallback()) {

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

        fun bind(user: User) =
            with(itemView) {
                loginTv.text = user.login
                avatarIv.load(user.avatarUrl)
            }
    }

    companion object {
        private const val USERS_OFFSET = 10
    }

}

private class DiffUtilItemCallback : DiffUtil.ItemCallback<User>() {
    override fun areItemsTheSame(oldUser: User, newUser: User) = oldUser.id == newUser.id
    override fun areContentsTheSame(oldUser: User, newUser: User) = oldUser == newUser
}