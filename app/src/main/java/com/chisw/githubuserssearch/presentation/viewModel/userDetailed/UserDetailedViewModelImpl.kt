package com.chisw.githubuserssearch.presentation.viewModel.userDetailed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.domain.model.UserRepos
import com.chisw.githubuserssearch.domain.usecase.GetUserFollowersUseCase
import com.chisw.githubuserssearch.domain.usecase.GetUserReposUseCase
import com.chisw.githubuserssearch.presentation.viewModel.base.BaseViewModelImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserDetailedViewModelImpl(
    private val userReposUseCase: GetUserReposUseCase,
    private val userFollowersUseCase: GetUserFollowersUseCase
) : BaseViewModelImpl(), UserDetailedViewModel {

    private val _userPublicReposLiveData: MutableLiveData<Int> = MutableLiveData()
    override val userPublicReposLiveData: LiveData<Int> = _userPublicReposLiveData

    private val _userFollowersLiveData: MutableLiveData<Int> = MutableLiveData()
    override val userFollowersLiveData: LiveData<Int> = _userFollowersLiveData

    private lateinit var masterUser: User

    override fun requestDataForMasterUser(user: User) {
        masterUser = user
        requestData()
    }

    private fun requestData() {
        viewModelScope.launch {
            val userRepos = getReposAsync()
            val userFollowers = getFollowersAsync()

            emitUserFollowers(userFollowers.await())
            emitUserPublicRepos(userRepos.await())
        }
    }

    private fun getReposAsync() = GlobalScope.async(Dispatchers.IO) {
        val userRepos = userReposUseCase(GetUserReposUseCase.Params.withParams(masterUser.login))
        userRepos.getOrNull()
    }

    private fun getFollowersAsync() = GlobalScope.async(Dispatchers.IO) {
        val userFollowers =
            userFollowersUseCase(GetUserFollowersUseCase.Params.withParams(masterUser.login))
        userFollowers.getOrNull()
    }

    private fun emitUserFollowers(userFollowers: List<User>?) =
        userFollowers?.size?.let { _userFollowersLiveData.value = it }

    private fun emitUserPublicRepos(userRepos: List<UserRepos>?) =
        userRepos?.let { _userPublicReposLiveData.value = it.filter { repo -> !repo.private }.size }

}