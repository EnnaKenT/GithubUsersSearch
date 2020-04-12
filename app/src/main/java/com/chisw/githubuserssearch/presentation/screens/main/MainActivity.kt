package com.chisw.githubuserssearch.presentation.screens.main

import androidx.lifecycle.observe
import com.chisw.githubuserssearch.R
import com.chisw.githubuserssearch.data.model.Hit
import com.chisw.githubuserssearch.presentation.screens.UsersViewModel
import com.chisw.githubuserssearch.presentation.screens.base.activity.BaseActivity
import com.chisw.githubuserssearch.presentation.screens.main.adapter.PostsAdapter
import com.chisw.githubuserssearch.presentation.screens.utils.addItemDecoration
import com.chisw.githubuserssearch.presentation.screens.utils.getSelectedItemsCount
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_menu_main.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: UsersViewModel by viewModel()
    private val adapter: PostsAdapter = PostsAdapter(::onAdapterItemClicked)

    private fun onAdapterItemClicked(hit: Hit) {
        cl_nav_view.navBarTv.text = getSelectedItemsCount(adapter)
    }

    override fun initViews() {
        with(recyclerView) {
            this.adapter = this@MainActivity.adapter
            this.addItemDecoration(context)
        }
//        swipyrefreshlayout.setOnRefreshListener { viewModel.refreshData() }
        viewModel.usersListLiveData.observe(this) {
//            swipyrefreshlayout.isRefreshing = false
            adapter.setItems(it)
        }
//        viewModel.refreshData()
    }

}