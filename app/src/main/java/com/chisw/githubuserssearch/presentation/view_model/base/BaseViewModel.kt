package com.chisw.githubuserssearch.presentation.view_model.base

import androidx.lifecycle.LiveData
import com.chisw.githubuserssearch.domain.exception.Failure

interface BaseViewModel {
    val failure: LiveData<Failure<*>>
    fun postFailure(failure: Failure<*>)
}