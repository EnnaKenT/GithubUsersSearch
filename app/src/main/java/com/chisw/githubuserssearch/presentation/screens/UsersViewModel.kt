package com.chisw.githubuserssearch.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chisw.githubuserssearch.data.model.GetPostsResponse
import com.chisw.githubuserssearch.data.model.Item
import com.chisw.githubuserssearch.domain.usecase.GetUsersByLoginUseCase
import com.chisw.githubuserssearch.domain.viewmodel.BaseViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.util.*

class UsersViewModel(private val getUsersByLogin: GetUsersByLoginUseCase) : BaseViewModel() {

    private val _usersListLiveData: MutableLiveData<List<Item>> = MutableLiveData(ArrayList())
    val usersListLiveData: LiveData<List<Item>> = _usersListLiveData
    private val _totalItemsCountLiveData: MutableLiveData<Int> = MutableLiveData()
    val totalItemsCountLiveData: LiveData<Int> = _totalItemsCountLiveData

    private var searchFirstPageJob: Job? = null
    private var searchNextPageJob: Job? = null
    private val usersList = mutableListOf<Item>()
    private var searchLogin = ""
    private var page = 1

    init {
        requestFirstPageUsers("aa")
    }

    fun requestFirstPageUsers(login: String) {
        page = 1 //reset page
        searchLogin = login
        searchFirstPageJob?.cancel() //cancel (in case of active request) if login changed
        searchFirstPageJob = viewModelScope.launch { requestUsers() }
    }

    fun requestNextPageUsers() {
        if (searchNextPageJob?.isCompleted == false) return //not request next page until we get response
        searchNextPageJob = viewModelScope.launch { requestUsers() }
    }

    private suspend fun requestUsers() {
        val users = getUsersByLogin(GetUsersByLoginUseCase.Params.withParams(searchLogin, page))
        users.getOrNull()?.let { updateData(it) }
    }

    private fun updateData(postsResponse: GetPostsResponse) {
        if (page == 1) usersList.clear() //clear list if first page response
        usersList.addAll(postsResponse.items)
        _usersListLiveData.value = usersList
        _totalItemsCountLiveData.value = postsResponse.total_count
        page++
    }

}