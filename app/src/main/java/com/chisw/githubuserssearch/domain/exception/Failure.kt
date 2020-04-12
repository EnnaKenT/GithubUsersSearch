package com.chisw.githubuserssearch.domain.exception

import com.chisw.githubuserssearch.domain.event.Event
import retrofit2.Response
import java.net.HttpURLConnection


sealed class Failure<T> (data : T): Event<T>(data) {

    class UnexpectedNetworkError : Failure<Unit>(Unit)
    class UnexpectedError(response: Response<*>) : Failure<Response<*>>(response)

    companion object {

        fun serverError(response: Response<*>): Failure<*> = when (response.code()) {
            HttpURLConnection.HTTP_NOT_ACCEPTABLE -> UnexpectedError(response)
            HttpURLConnection.HTTP_NOT_FOUND,
            HttpURLConnection.HTTP_INTERNAL_ERROR,
            HttpURLConnection.HTTP_NOT_IMPLEMENTED,
            HttpURLConnection.HTTP_BAD_GATEWAY,
            HttpURLConnection.HTTP_UNAVAILABLE -> UnexpectedError(response)
            else -> UnexpectedError(response)
        }
    }

}