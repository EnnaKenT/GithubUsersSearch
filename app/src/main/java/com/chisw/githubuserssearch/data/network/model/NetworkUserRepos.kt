package com.chisw.githubuserssearch.data.network.model

import com.chisw.githubuserssearch.domain.model.UserRepos

data class NetworkUserRepos(
    val id: Int,
    val name: String,
    val private: Boolean
)

fun NetworkUserRepos.toDomainModel() =
    UserRepos(id, name, private)

fun UserRepos.toDataModel(): NetworkUserRepos =
    NetworkUserRepos(id, name, private)