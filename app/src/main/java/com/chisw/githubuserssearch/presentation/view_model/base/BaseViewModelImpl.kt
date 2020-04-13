package com.chisw.githubuserssearch.presentation.view_model.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.domain.functional.Either

abstract class BaseViewModelImpl : ViewModel(), BaseViewModel {

    private val _failure = MutableLiveData<Failure<*>>()
    override val failure: LiveData<Failure<*>> = _failure

    override fun postFailure(failure: Failure<*>) {
        _failure.postValue(failure)
    }

    protected fun <R> Either<Failure<*>, R>.handle(handler: (R) -> Unit = {}): Any =
        either(::postFailure, handler)

    protected inline fun <reified R : Any> Either<Failure<*>, R>.getOrNull(): R? =
        either(::postFailure) { it } as? R

    protected inline fun <reified R : Any> Either<Failure<*>, R>.get(default: R): R =
        getOrNull() ?: default

}