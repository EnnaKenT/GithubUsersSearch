package com.chisw.githubuserssearch.data.network.service

import com.chisw.githubuserssearch.data.network.api.GitHubApi
import com.chisw.githubuserssearch.data.network.model.NetworkUser
import com.chisw.githubuserssearch.data.network.model.NetworkUserRepos
import com.chisw.githubuserssearch.data.network.model.NetworkUsers
import retrofit2.Call

interface GitHubService {

    suspend fun getUsersByLogin(login: String, page: Int): Call<NetworkUsers>
    suspend fun getUserRepos(login: String): Call<List<NetworkUserRepos>>
    suspend fun getUserFollowers(login: String): Call<List<NetworkUser>>

    class GitHubServiceImpl (private val gitHubApi: GitHubApi) : GitHubService {

        override suspend fun getUsersByLogin(login: String, page: Int): Call<NetworkUsers> =
            gitHubApi.getUsersByLogin(login = login, page = page)

        override suspend fun getUserRepos(login: String): Call<List<NetworkUserRepos>> =
            gitHubApi.getUserRepos(login)

        override suspend fun getUserFollowers(login: String): Call<List<NetworkUser>> =
            gitHubApi.getUserFollowers(login)

    }

}