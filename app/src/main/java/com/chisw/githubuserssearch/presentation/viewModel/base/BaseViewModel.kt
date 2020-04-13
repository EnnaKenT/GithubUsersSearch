package com.chisw.githubuserssearch.presentation.viewModel.base

import androidx.lifecycle.LiveData
import com.chisw.githubuserssearch.domain.exception.Failure

interface BaseViewModel {
    val failure: LiveData<Failure<*>>
    fun postFailure(failure: Failure<*>)
}