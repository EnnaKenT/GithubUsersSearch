package com.chisw.githubuserssearch.data.network.api

import com.chisw.githubuserssearch.data.network.model.NetworkUser
import com.chisw.githubuserssearch.data.network.model.NetworkUserRepos
import com.chisw.githubuserssearch.data.network.model.NetworkUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    companion object {
        const val TYPE = "users"
        const val SORT = "stars"
        const val ORDER = "desc"
        const val PER_PAGE = 100
        private const val LOGIN_PATH = "login"
    }

    @GET("/search/users")
    fun getUsersByLogin(
        @Query("q") login: String,
        @Query("type") type: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<NetworkUsers>

    @GET("/users/{$LOGIN_PATH}/repos")
    fun getUserRepos(@Path(LOGIN_PATH) login: String): Call<List<NetworkUserRepos>>

    @GET("/users/{$LOGIN_PATH}/followers")
    fun getUserFollowers(@Path(LOGIN_PATH) login: String): Call<List<NetworkUser>>

}