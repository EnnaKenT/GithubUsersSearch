package com.chisw.githubuserssearch.data.mapper

import com.chisw.githubuserssearch.data.network.model.NetworkUser
import com.chisw.githubuserssearch.data.network.model.NetworkUserRepos
import com.chisw.githubuserssearch.data.network.model.NetworkUsers
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.domain.model.UserRepos
import com.chisw.githubuserssearch.domain.model.Users

/**
 * Converts models from data to domain an vice versa
 */

/**
 * Users and User mapper
 */
fun NetworkUsers.toDomainModel() = Users(users.map { it.toDomainModel() }, totalCount)
fun Users.toDataModel(): NetworkUsers = NetworkUsers(users.map { it.toDataModel() }, totalCount)
fun NetworkUser.toDomainModel() = User(login, id, avatarUrl, url)
fun User.toDataModel(): NetworkUser = NetworkUser(login, id, avatarUrl, url)

/**
 * Repos mapper
 */
fun NetworkUserRepos.toDomainModel() = UserRepos(id, name, private)
fun UserRepos.toDataModel(): NetworkUserRepos = NetworkUserRepos(id, name, private)