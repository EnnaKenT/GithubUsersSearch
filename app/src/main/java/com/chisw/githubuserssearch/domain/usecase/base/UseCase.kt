package com.chisw.githubuserssearch.domain.usecase.base

import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.functional.Either
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlin.coroutines.CoroutineContext

abstract class UseCase<out Type, in Params>(
    private val context: CoroutineContext = Dispatchers.IO
) where Type : Any {

    protected abstract suspend fun run(params: Params): Either<Failure<*>, Type>

    suspend operator fun invoke(params: Params): Either<Failure<*>, Type> =
        withContext(context) {
            run(params)
        }

    object None

}