package com.chisw.githubuserssearch.data.network.service

import com.chisw.githubuserssearch.data.network.api.GitHubApi
import com.chisw.githubuserssearch.data.network.model.NetworkUser
import com.chisw.githubuserssearch.data.network.model.NetworkUserRepos
import com.chisw.githubuserssearch.data.network.model.NetworkUsers
import retrofit2.Call

interface GitHubService {

    fun getUsersByLogin(login: String, page: Int): Call<NetworkUsers>
    fun getUserRepos(login: String): Call<List<NetworkUserRepos>>
    fun getUserFollowers(login: String): Call<List<NetworkUser>>

    class GitHubServiceImpl (private val gitHubApi: GitHubApi) : GitHubService {

        override fun getUsersByLogin(login: String, page: Int): Call<NetworkUsers> =
            gitHubApi.getUsersByLogin(login = login, page = page)

        override fun getUserRepos(login: String): Call<List<NetworkUserRepos>> =
            gitHubApi.getUserRepos(login)

        override fun getUserFollowers(login: String): Call<List<NetworkUser>> =
            gitHubApi.getUserFollowers(login)

    }

}