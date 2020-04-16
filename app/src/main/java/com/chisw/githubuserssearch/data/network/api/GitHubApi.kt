package com.chisw.githubuserssearch.data.network.api

import com.chisw.githubuserssearch.data.network.model.NetworkUser
import com.chisw.githubuserssearch.data.network.model.NetworkUserRepos
import com.chisw.githubuserssearch.data.network.model.NetworkUsers
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/users")
    fun getUsersByLogin(
        @Query("q") login: String,
        @Query("type") type: String = Http.Query.TYPE,
        @Query("sort") sort: String = Http.Query.SORT,
        @Query("order") order: String = Http.Query.ORDER,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = Http.Query.PER_PAGE
    ): Call<NetworkUsers>

    @GET("/users/{${Http.Path.LOGIN}}/repos")
    fun getUserRepos(@Path(Http.Path.LOGIN) login: String): Call<List<NetworkUserRepos>>

    @GET("/users/{${Http.Path.LOGIN}}/followers")
    fun getUserFollowers(@Path(Http.Path.LOGIN) login: String): Call<List<NetworkUser>>

}