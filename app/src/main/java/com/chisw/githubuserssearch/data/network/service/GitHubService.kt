package com.chisw.githubuserssearch.data.network.service

import com.chisw.githubuserssearch.data.network.api.GitHubApi
import com.chisw.githubuserssearch.data.network.model.NetworkUsers
import retrofit2.Call

interface GitHubService {

    companion object {
        private const val TYPE = "users"
        private const val SORT = "stars"
        private const val ORDER = "desc"
        private const val PER_PAGE = 100
    }

    fun getUsersByLogin(login: String, page: Int): Call<NetworkUsers>

    class GitHubServiceImpl constructor(private val gitHubApi: GitHubApi) : GitHubService {

        override fun getUsersByLogin(login: String, page: Int): Call<NetworkUsers> =
            gitHubApi.getUsersByLogin(login, TYPE, SORT, ORDER, page, PER_PAGE)

    }

}