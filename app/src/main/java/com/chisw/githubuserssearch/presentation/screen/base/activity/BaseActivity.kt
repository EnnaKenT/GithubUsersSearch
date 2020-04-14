package com.chisw.githubuserssearch.presentation.screen.base.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.observe
import com.chisw.githubuserssearch.domain.exception.Failure
import com.chisw.githubuserssearch.presentation.utils.toast
import com.chisw.githubuserssearch.presentation.view_model.base.BaseViewModel

abstract class BaseActivity<VM : BaseViewModel>(@LayoutRes layoutId: Int) :
    AppCompatActivity(layoutId) {

    abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
        initBaseLogic()
    }

    abstract fun initViews()

    private fun initBaseLogic() {
        viewModel.failure.observe(this, ::showToast)
    }

    fun showToast(message: Failure<*>) = toast(message)

}