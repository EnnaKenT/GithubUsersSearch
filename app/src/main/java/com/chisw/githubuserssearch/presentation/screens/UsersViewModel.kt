package com.chisw.githubuserssearch.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chisw.githubuserssearch.data.model.Item
import com.chisw.githubuserssearch.domain.usecase.GetUsersByLoginUseCase
import com.chisw.githubuserssearch.domain.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
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

    fun searchQueryChanged(text: String) = requestFirstPageUsers(text, QUERY_DELAY)

    fun searchQuerySubmitted(text: String) = requestFirstPageUsers(text)

    private fun requestFirstPageUsers(login: String, delay: Long = 0) {
        searchFirstPageJob?.cancel() //cancel (in case of active request) if login changed
        searchFirstPageJob = viewModelScope.launch {
            withContext(Dispatchers.IO) { Thread.sleep(delay) } //delay in case if user is still typing
            page = 1 //reset page
            searchLogin = login
            requestUsers()
        }
    }

    fun requestNextPageUsers() {
        if (searchNextPageJob?.isCompleted == false) return //not request next page until we get response
        searchNextPageJob = viewModelScope.launch { requestUsers() }
    }

    private suspend fun requestUsers() {
        if (searchLogin.isEmpty()) {
            emitData(mutableListOf(), 0)
        } else {
            val users = getUsersByLogin(GetUsersByLoginUseCase.Params.withParams(searchLogin, page))
            users.getOrNull()?.let { emitData(it.items, it.total_count) }
        }
    }

    private fun emitData(items: List<Item>, totalCount: Int) {
        if (page == 1) usersList.clear() //clear list if first page response
        usersList.addAll(items)
        _usersListLiveData.value = usersList
        _totalItemsCountLiveData.value = totalCount
        page++
    }

    companion object {
        private const val QUERY_DELAY: Long = 300
    }

}