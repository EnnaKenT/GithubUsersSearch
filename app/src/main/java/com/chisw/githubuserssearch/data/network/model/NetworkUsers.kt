package com.chisw.githubuserssearch.data.network.model

import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.domain.model.Users
import com.google.gson.annotations.SerializedName

data class NetworkUsers(
    @SerializedName("items")
    val users: List<NetworkUser> = emptyList(),
    @SerializedName("total_count")
    val totalCount: Int = 0
)

fun NetworkUsers.toDomainModel() = Users(users.map { it.toDomainModel() }, totalCount)

fun Users.toDataModel(): NetworkUsers = NetworkUsers(users.map { it.toDataModel() }, totalCount)

fun NetworkUser.toDomainModel() = User(login, id, avatarUrl, url)

fun User.toDataModel(): NetworkUser = NetworkUser(login, id, avatarUrl, url)

data class NetworkUser(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String,
    val url: String
)