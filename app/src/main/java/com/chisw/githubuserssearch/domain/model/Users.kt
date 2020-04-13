package com.chisw.githubuserssearch.domain.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Users(
    val users: List<User>,
    val totalCount: Int
) : Parcelable

@Parcelize
data class User(
    val login: String,
    val id: Int,
    val avatarUrl: String,
    val url: String
) : Parcelable {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as User
        return login == other.login && avatarUrl == other.avatarUrl && id == other.id && url == other.url
    }

    override fun hashCode(): Int =
        login.hashCode() + avatarUrl.hashCode() + id.hashCode() + url.hashCode()
}