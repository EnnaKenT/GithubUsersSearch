package com.chisw.githubuserssearch.data.model

data class GetPostsResponse(
    val hits: List<Hit>,
    val page: Int,
    val nbPages: Int,
    val hitsPerPage: Int
)

data class Hit(val created_at: String, val title: String, val author: String) {

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        other as Hit
        return created_at == other.created_at && title == other.title && author == other.author
    }

    override fun hashCode(): Int = created_at.hashCode() + title.hashCode() + author.hashCode()
}