package com.chisw.githubuserssearch.data.network.api

import com.chisw.githubuserssearch.data.model.GetPostsResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface GitHubApi {

    @GET("/search/users?q=Tetris&type=Users&sort=stars&order=desc&page=0&per_page=100")
    fun getUsersByLogin(
        @Query("q") login: String,
        @Query("type") type: String,
        @Query("sort") sort: String,
        @Query("order") order: String,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int
    ): Call<GetPostsResponse>

}