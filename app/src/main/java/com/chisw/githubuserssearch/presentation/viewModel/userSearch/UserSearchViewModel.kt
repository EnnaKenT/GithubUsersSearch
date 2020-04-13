package com.chisw.githubuserssearch.presentation.viewModel.userSearch

import androidx.lifecycle.LiveData
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.presentation.viewModel.base.BaseViewModel

interface UserSearchViewModel : BaseViewModel {
    val usersListLiveData: LiveData<List<User>>
    val totalItemsCountLiveData: LiveData<Int>
    fun searchQueryChanged(text: String)
    fun searchQuerySubmitted(text: String)
    fun requestNextPageUsers()
}