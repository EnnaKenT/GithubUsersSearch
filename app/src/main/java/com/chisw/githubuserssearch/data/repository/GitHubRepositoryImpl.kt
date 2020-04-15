package com.chisw.githubuserssearch.data.repository

import com.chisw.githubuserssearch.data.network.model.NetworkUsers
import com.chisw.githubuserssearch.data.network.model.toDomainModel
import com.chisw.githubuserssearch.data.network.service.GitHubService
import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.functional.Either
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.domain.model.UserRepos
import com.chisw.githubuserssearch.domain.model.Users
import com.chisw.githubuserssearch.domain.repository.GitHubRepository

class GitHubRepositoryImpl(private val postsService: GitHubService) : GitHubRepository {

    override suspend fun getUsersByLogin(login: String, page: Int): Either<Failure<*>, Users> =
        request(
            postsService.getUsersByLogin(login, page),
            { it.toDomainModel() },
            NetworkUsers()
        )

    override suspend fun getUserRepos(login: String): Either<Failure<*>, List<UserRepos>> =
        request(
            postsService.getUserRepos(login),
            { it.map { item -> item.toDomainModel() } },
            emptyList()
        )

    override suspend fun getUserFollowers(login: String): Either<Failure<*>, List<User>> =
        request(
            postsService.getUserFollowers(login),
            { it.map { item -> item.toDomainModel() } },
            emptyList()
        )

}