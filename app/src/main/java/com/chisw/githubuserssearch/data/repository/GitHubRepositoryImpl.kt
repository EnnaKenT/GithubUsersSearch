package com.chisw.githubuserssearch.data.repository

import com.chisw.githubuserssearch.data.network.model.NetworkUsers
import com.chisw.githubuserssearch.data.network.model.toDomainModel
import com.chisw.githubuserssearch.data.network.service.GitHubService
import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.model.Users
import com.chisw.githubuserssearch.domain.repository.GitHubRepository
import com.chisw.githubuserssearch.domain.functional.Either

class GitHubRepositoryImpl(private val postsService: GitHubService) : GitHubRepository {

    override fun getUsersByLogin(login: String, page: Int): Either<Failure<*>, Users> =
        request(
            postsService.getUsersByLogin(login, page),
            { it.toDomainModel() },
            NetworkUsers()
        )

}