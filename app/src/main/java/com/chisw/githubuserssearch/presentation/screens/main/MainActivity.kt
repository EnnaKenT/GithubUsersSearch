package com.chisw.githubuserssearch.presentation.screens.main

import android.view.Menu
import android.widget.SearchView
import androidx.lifecycle.observe
import com.chisw.githubuserssearch.R
import com.chisw.githubuserssearch.data.model.Item
import com.chisw.githubuserssearch.presentation.screens.UsersViewModel
import com.chisw.githubuserssearch.presentation.screens.base.activity.BaseActivity
import com.chisw.githubuserssearch.presentation.screens.main.adapter.UsersAdapter
import com.chisw.githubuserssearch.presentation.utils.addItemDecoration
import com.chisw.githubuserssearch.presentation.utils.setBottomBarExpandListener
import com.chisw.githubuserssearch.presentation.utils.show
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main), SearchView.OnQueryTextListener {

    private val viewModel: UsersViewModel by viewModel()
    private val adapter by lazy {
        UsersAdapter(viewModel::requestNextPageUsers, ::onAdapterItemClicked)
    }

    private fun onAdapterItemClicked(item: Item) {
        // open detailed screen
    }

    override fun initViews() {
        setSupportActionBar(bottomAppBar)
        with(recyclerView) {
            this.adapter = this@MainActivity.adapter
            this.addItemDecoration(context)
        }
        viewModel.usersListLiveData.observe(this, ::updateAdapterItems)
        viewModel.totalItemsCountLiveData.observe(this) { adapter.totalItemsCount = it }
        viewModel.failure.observe(this) { showToast(it) }
    }

    private fun updateAdapterItems(items: List<Item>) {
        recyclerView.show(items.isNotEmpty())
        noItemsTv.show(items.isEmpty())
        adapter.setItems(items)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_bottom_bar, menu)
        menu.findItem(R.id.action_search).run {
            setBottomBarExpandListener(this@MainActivity)
            val searchView = actionView as SearchView
            searchView.run {
                queryHint = context.getString(R.string.enter_login_hint)
                setOnQueryTextListener(this@MainActivity)
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