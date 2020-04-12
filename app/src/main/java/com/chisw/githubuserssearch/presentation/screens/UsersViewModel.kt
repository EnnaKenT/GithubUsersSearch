package com.chisw.githubuserssearch.presentation.screens

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chisw.githubuserssearch.data.model.Hit
import com.chisw.githubuserssearch.domain.usecase.GetUsersByLoginUseCase
import com.chisw.githubuserssearch.domain.viewmodel.BaseViewModel
import kotlinx.coroutines.*
import java.util.*

class UsersViewModel(private val getUsersByLogin: GetUsersByLoginUseCase) : BaseViewModel() {

    private var page = 0
        get() = field++

    private val usersList = mutableListOf<Hit>()
    private val _usersListLiveData = MutableLiveData<List<Hit>>(ArrayList())
    val usersListLiveData: LiveData<List<Hit>> = _usersListLiveData

    fun getFirstPageUsers(login: String) =
        viewModelScope.launch {
            page = 0
            val users = getUsersByLogin(GetUsersByLoginUseCase.Params.withParams(login, page))
            users.getOrNull()?.let {
                usersList.clear()
                usersList.addAll(it.hits)
                _usersListLiveData.value = usersList
            }
        }

    fun getNextPage(login: String) {
        val job: Job = GlobalScope.launch(Dispatchers.Main) {

        }
    }

}