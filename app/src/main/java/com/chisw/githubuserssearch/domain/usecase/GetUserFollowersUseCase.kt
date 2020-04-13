package com.chisw.githubuserssearch.domain.usecase

import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.functional.Either
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.domain.repository.GitHubRepository
import com.chisw.githubuserssearch.domain.usecase.base.UseCase

class GetUserFollowersUseCase(
    private val gitHubRepository: GitHubRepository
) : UseCase<List<User>, GetUserFollowersUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure<*>, List<User>> =
        gitHubRepository.getUserFollowers(params.login)

    class Params private constructor(val login: String) {
        companion object {
            fun withParams(login: String): Params = Params(login)
        }
    }

}