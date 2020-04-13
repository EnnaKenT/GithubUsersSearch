package com.chisw.githubuserssearch.presentation.view_model.userDetailed

import androidx.lifecycle.LiveData
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.presentation.view_model.base.BaseViewModel

interface UserDetailedViewModel : BaseViewModel {
    val userPublicReposLiveData: LiveData<Int>
    val userFollowersLiveData: LiveData<Int>
    fun requestDataForMasterUser(user: User)
}