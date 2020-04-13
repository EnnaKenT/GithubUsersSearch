package com.chisw.githubuserssearch.domain.repository

import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.model.Users
import com.chisw.githubuserssearch.domain.repository.base.Repository
import com.chisw.githubuserssearch.domain.functional.Either

interface GitHubRepository : Repository {

    fun getUsersByLogin(login: String, page: Int): Either<Failure<*>, Users>

}