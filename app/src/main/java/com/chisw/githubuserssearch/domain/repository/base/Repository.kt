package com.chisw.githubuserssearch.domain.repository.base

import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.presentation.functional.Either
import com.chisw.githubuserssearch.presentation.functional.Either.Left
import com.chisw.githubuserssearch.presentation.functional.Either.Right
import retrofit2.Call

interface Repository {

    fun <T, R> request(call: Call<T>, transform: (T) -> R, default: T): Either<Failure<*>, R> {
        return try {
            val response = call.execute()
            when (response.isSuccessful) {
                true -> Right(transform((response.body() ?: default)))
                false -> Left(Failure.serverError(response))
            }
        } catch (e: Throwable) {
            Left(Failure.UnexpectedNetworkError())
        }
    }

}