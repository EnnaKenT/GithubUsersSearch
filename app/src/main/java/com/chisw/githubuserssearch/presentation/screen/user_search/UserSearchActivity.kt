package com.chisw.githubuserssearch.presentation.screen.user_search

import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.observe
import com.chisw.githubuserssearch.R
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.presentation.screen.base.activity.BaseActivity
import com.chisw.githubuserssearch.presentation.screen.user_detailed.UserDetailedActivity
import com.chisw.githubuserssearch.presentation.screen.user_search.adapter.UsersAdapter
import com.chisw.githubuserssearch.presentation.utils.addItemDecoration
import com.chisw.githubuserssearch.presentation.utils.customViewModel
import com.chisw.githubuserssearch.presentation.utils.setBottomBarExpandListener
import com.chisw.githubuserssearch.presentation.utils.show
import kotlinx.android.synthetic.main.activity_user_search.*

class UserSearchActivity : BaseActivity(R.layout.activity_user_search),
    SearchView.OnQueryTextListener {

    private val viewModel by customViewModel<UserSearchViewModel>()
    private val adapter by lazy {
        UsersAdapter(viewModel::requestNextPageUsers, ::onAdapterItemClicked)
    }

    private fun onAdapterItemClicked(user: User) = UserDetailedActivity.startActivity(this, user)

    override fun initViews() {
        setSupportActionBar(bottomAppBar)
        with(recyclerView) {
            this.adapter = this@UserSearchActivity.adapter
            this.addItemDecoration(context)
        }
        viewModel.usersListLiveData.observe(this, ::updateAdapterItems)
        viewModel.totalItemsCountLiveData.observe(this) { adapter.totalItemsCount = it }
        viewModel.failure.observe(this) { showToast(it) }
    }

    private fun updateAdapterItems(users: List<User>) {
        recyclerView.show(users.isNotEmpty())
        noItemsTv.show(users.isEmpty())
        adapter.setItems(users)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_bottom_bar, menu)
        menu.findItem(R.id.action_search).run {
            setBottomBarExpandListener(this@UserSearchActivity)
            val searchView = actionView as SearchView
            searchView.run {
                queryHint = context.getString(R.string.enter_login_hint)
                setOnQueryTextListener(this@UserSearchActivity)
                isIconifiedByDefault = false // Do not iconify the widget; expand it by default
            }
        }
        return true
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        query?.let { viewModel.searchQuerySubmitted(it) }
        return true
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        newText?.let { viewModel.searchQueryChanged(it) }
        return true
    }

}