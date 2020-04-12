package com.chisw.githubuserssearch.presentation.screens.main

import androidx.lifecycle.observe
import com.chisw.githubuserssearch.R
import com.chisw.githubuserssearch.data.model.Item
import com.chisw.githubuserssearch.presentation.screens.UsersViewModel
import com.chisw.githubuserssearch.presentation.screens.base.activity.BaseActivity
import com.chisw.githubuserssearch.presentation.screens.main.adapter.UsersAdapter
import com.chisw.githubuserssearch.presentation.utils.addItemDecoration
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: UsersViewModel by viewModel()
    private val adapter by lazy {
        UsersAdapter(viewModel::requestNextPageUsers, ::onAdapterItemClicked)
    }

    private fun onAdapterItemClicked(item: Item) {
        // open detailed screen
    }

    override fun initViews() {
        with(recyclerView) {
            this.adapter = this@MainActivity.adapter
            this.addItemDecoration(context)
        }
        viewModel.usersListLiveData.observe(this, adapter::setItems)
        viewModel.totalItemsCountLiveData.observe(this) { adapter.totalItemsCount = it }
        viewModel.failure.observe(this) { showToast(it) }
    }

}