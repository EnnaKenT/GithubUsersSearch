package com.chisw.githubuserssearch.domain.usecase

import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.model.Users
import com.chisw.githubuserssearch.domain.repository.GitHubRepository
import com.chisw.githubuserssearch.domain.usecase.base.UseCase
import com.chisw.githubuserssearch.domain.functional.Either

class GetUsersByLoginUseCase(
    private val gitHubRepository: GitHubRepository
) : UseCase<Users, GetUsersByLoginUseCase.Params>() {

    override suspend fun run(params: Params): Either<Failure<*>, Users> =
        gitHubRepository.getUsersByLogin(params.login, params.page)

    class Params private constructor(val login: String, val page: Int) {
        companion object {
            fun withParams(login: String, page: Int): Params = Params(login, page)
        }
    }

}