package com.chisw.githubuserssearch.presentation.screen.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.presentation.utils.customViewModel
import com.chisw.githubuserssearch.presentation.utils.toast
import com.chisw.githubuserssearch.presentation.viewModel.base.BaseViewModel

abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    abstract fun initViews()

    fun showToast(message: Failure<*>) = toast(message)

}