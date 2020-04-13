package com.chisw.githubuserssearch.domain.usecase

import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.functional.Either
import com.chisw.githubuserssearch.domain.model.UserRepos
import com.chisw.githubuserssearch.domain.repository.GitHubRepository
import com.chisw.githubuserssearch.domain.usecase.base.UseCase

class GetUserReposUseCase(
    private val gitHubRepository: GitHubRepository
) : UseCase<List<UserRepos>, GetUserReposUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure<*>, List<UserRepos>> =
        gitHubRepository.getUserRepos(params.login)

    class Params private constructor(val login: String) {
        companion object {
            fun withParams(login: String): Params = Params(login)
        }
    }

}