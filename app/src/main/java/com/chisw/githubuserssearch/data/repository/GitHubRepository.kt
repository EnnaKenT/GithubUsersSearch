package com.chisw.githubuserssearch.data.repository

import com.chisw.githubuserssearch.data.model.GetPostsResponse
import com.chisw.githubuserssearch.data.network.api.GitHubService
import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.repository.base.Repository
import com.chisw.githubuserssearch.presentation.base.functional.Either

interface GitHubRepository : Repository {

    fun getUsersByLogin(login: String, page: Int): Either<Failure<*>, GetPostsResponse>

    class Network(private val postsService: GitHubService) : Repository.BaseNetwork(),
        GitHubRepository {

        override fun getUsersByLogin(
            login: String,
            page: Int
        ): Either<Failure<*>, GetPostsResponse> = request(postsService.getUsersByLogin(login, page))

    }

}