package com.chisw.githubuserssearch.domain.usecase

import com.chisw.githubuserssearch.data.model.GetPostsResponse
import com.chisw.githubuserssearch.data.repository.GitHubRepository
import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.usecase.base.UseCase
import com.chisw.githubuserssearch.presentation.base.functional.Either

class GetUsersByLoginUseCase(
    private val gitHubRepository: GitHubRepository
) : UseCase<GetPostsResponse, GetUsersByLoginUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure<*>, GetPostsResponse> =
        gitHubRepository.getUsersByLogin(params.login, params.page)

    class Params private constructor(val login: String, val page: Int) {
        companion object {
            fun withParams(login: String, page: Int): Params = Params(login, page)
        }
    }

}