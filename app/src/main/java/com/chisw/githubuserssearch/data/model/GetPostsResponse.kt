package com.chisw.githubuserssearch.data.model

data class GetPostsResponse(
    val items: List<Item>,
    val incomplete_results: Boolean,
    val total_count: Int
)

data class Item(val login: String, val id: Int, val avatar_url: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as Item
        return login == other.login && avatar_url == other.avatar_url && id == other.id
    }

    override fun hashCode(): Int = login.hashCode() + avatar_url.hashCode() + id.hashCode()
}