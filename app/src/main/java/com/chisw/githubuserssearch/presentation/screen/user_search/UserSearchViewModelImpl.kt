package com.chisw.githubuserssearch.presentation.screen.user_search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.domain.usecase.GetUsersByLoginUseCase
import com.chisw.githubuserssearch.presentation.view_model.base.BaseViewModelImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*

class UserSearchViewModelImpl(private val usersByLoginUseCase: GetUsersByLoginUseCase) :
    BaseViewModelImpl(), UserSearchViewModel {

    private val _usersLiveData: MutableLiveData<List<User>> = MutableLiveData(ArrayList())
    override val usersLiveData: LiveData<List<User>> = _usersLiveData

    private val _totalUsersCountLiveData: MutableLiveData<Int> = MutableLiveData()
    override val totalUsersCountLiveData: LiveData<Int> = _totalUsersCountLiveData

    private var searchFirstPageJob: Job? = null
    private var searchNextPageJob: Job? = null

    private val usersList = mutableListOf<User>()
    private var searchLogin = ""
    private var page = 1

    override fun searchQueryChanged(text: String) = requestFirstPageUsers(text, true)

    override fun searchQuerySubmitted(text: String) = requestFirstPageUsers(text)

    private fun requestFirstPageUsers(login: String, isDelayed: Boolean = false) {
        searchFirstPageJob?.cancel() //cancel (in case of active request) if login changed
        searchFirstPageJob = viewModelScope.launch {
            //delay in case if user is still typing
            if (isDelayed) withContext(Dispatchers.IO) { Thread.sleep(QUERY_DELAY) }
            page = 1 //reset page
            searchLogin = login
            requestUsers()
        }
    }

    override fun requestNextPageUsers() {
        if (searchNextPageJob?.isCompleted == false) return //not request next page until we get response
        searchNextPageJob = viewModelScope.launch { requestUsers() }
    }

    private suspend fun requestUsers() {
        if (searchLogin.isEmpty()) {
            emitData(mutableListOf(), 0)
        } else {
            val users =
                usersByLoginUseCase(GetUsersByLoginUseCase.Params.withParams(searchLogin, page))
            users.getOrNull()?.let { emitData(it.users, it.totalCount) }
        }
    }

    private fun emitData(users: List<User>, totalCount: Int) {
        if (page == 1) usersList.clear() //clear list if first page response
        usersList.addAll(users)
        _usersLiveData.value = usersList
        _totalUsersCountLiveData.value = totalCount
        page++
    }

    companion object {
        private const val QUERY_DELAY: Long = 300
    }

}