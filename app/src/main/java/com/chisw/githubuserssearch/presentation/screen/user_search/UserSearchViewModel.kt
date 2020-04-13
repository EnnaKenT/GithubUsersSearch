package com.chisw.githubuserssearch.presentation.screen.user_search

import androidx.lifecycle.LiveData
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.presentation.view_model.base.BaseViewModel

interface UserSearchViewModel : BaseViewModel {
    val usersListLiveData: LiveData<List<User>>
    val totalItemsCountLiveData: LiveData<Int>
    fun searchQueryChanged(text: String)
    fun searchQuerySubmitted(text: String)
    fun requestNextPageUsers()
}