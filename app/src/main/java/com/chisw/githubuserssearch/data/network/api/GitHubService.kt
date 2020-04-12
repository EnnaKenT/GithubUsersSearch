package com.chisw.githubuserssearch.data.network.api

import com.chisw.githubuserssearch.data.model.GetPostsResponse
import retrofit2.Call
import retrofit2.Retrofit

interface GitHubService {

    companion object {
        private const val TYPE = "users"
        private const val SORT = "stars"
        private const val ORDER = "desc"
        private const val PER_PAGE = 100
    }

    fun getUsersByLogin(login: String, page: Int): Call<GetPostsResponse>

    class GitHubServiceImpl constructor(retrofit: Retrofit) : GitHubService {

        private val gitHubApi by lazy { retrofit.create(GitHubApi::class.java) }

        override fun getUsersByLogin(login: String, page: Int): Call<GetPostsResponse> =
            gitHubApi.getUsersByLogin(login, TYPE, SORT, ORDER, page, PER_PAGE)

    }

}