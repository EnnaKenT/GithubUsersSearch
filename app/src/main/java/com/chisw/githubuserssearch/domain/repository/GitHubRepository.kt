package com.chisw.githubuserssearch.domain.repository

import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.functional.Either
import com.chisw.githubuserssearch.domain.model.User
import com.chisw.githubuserssearch.domain.model.UserRepos
import com.chisw.githubuserssearch.domain.model.Users
import com.chisw.githubuserssearch.domain.repository.base.Repository

interface GitHubRepository : Repository {

    fun getUsersByLogin(login: String, page: Int): Either<Failure<*>, Users>

    fun getUserRepos(login: String): Either<Failure<*>, List<UserRepos>>

    fun getUserFollowers(login: String): Either<Failure<*>, List<User>>

}