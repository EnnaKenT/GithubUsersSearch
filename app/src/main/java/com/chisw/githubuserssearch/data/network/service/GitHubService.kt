package com.chisw.githubuserssearch.data.network.service

import com.chisw.githubuserssearch.data.network.model.NetworkUsers
import com.chisw.githubuserssearch.data.network.api.GitHubApi
import retrofit2.Call
import retrofit2.Retrofit

interface GitHubService {

    companion object {
        private const val TYPE = "users"
        private const val SORT = "stars"
        private const val ORDER = "desc"
        private const val PER_PAGE = 100
    }

    fun getUsersByLogin(login: String, page: Int): Call<NetworkUsers>

    class GitHubServiceImpl constructor(retrofit: Retrofit) : GitHubService {

        private val gitHubApi by lazy { retrofit.create(GitHubApi::class.java) }

        override fun getUsersByLogin(login: String, page: Int): Call<NetworkUsers> =
            gitHubApi.getUsersByLogin(login, TYPE, SORT, ORDER, page, PER_PAGE)

    }

}