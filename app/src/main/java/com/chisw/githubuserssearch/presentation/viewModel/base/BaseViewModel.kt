package com.chisw.githubuserssearch.presentation.viewModel.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.presentation.functional.Either

abstract class BaseViewModel : ViewModel() {

    private val _failure = MutableLiveData<Failure<*>>()
    val failure: LiveData<Failure<*>> = _failure

    fun postFailure(failure: Failure<*>) {
        _failure.value = failure
    }

    protected fun <R> Either<Failure<*>, R>.handle(handler: (R) -> Unit = {}): Any =
        either(::postFailure, handler)

    protected inline fun <reified R : Any> Either<Failure<*>, R>.getOrNull(): R? =
        either(::postFailure) { it } as? R

    protected inline fun <reified R : Any> Either<Failure<*>, R>.get(default: R): R =
        getOrNull() ?: default

}