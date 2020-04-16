package com.chisw.githubuserssearch.data.network.model

import com.google.gson.annotations.SerializedName

data class NetworkUsers(
    @SerializedName("items")
    val users: List<NetworkUser> = emptyList(),
    @SerializedName("total_count")
    val totalCount: Int = 0
)

data class NetworkUser(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String
)