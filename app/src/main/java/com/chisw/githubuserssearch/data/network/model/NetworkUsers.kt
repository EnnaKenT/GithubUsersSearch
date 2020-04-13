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

fun NetworkUsers.toDomainModel() =
    Users(users.map { User(it.login, it.id, it.avatarUrl) }, totalCount)

fun Users.toDataModel(): NetworkUsers =
    NetworkUsers(users.map { NetworkUser(it.login, it.id, it.avatarUrl) }, totalCount)

data class NetworkUser(
    val login: String,
    val id: Int,
    @SerializedName("avatar_url")
    val avatarUrl: String
) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as NetworkUser
        return login == other.login && avatarUrl == other.avatarUrl && id == other.id
    }

    override fun hashCode(): Int = login.hashCode() + avatarUrl.hashCode() + id.hashCode()
}