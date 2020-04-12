package com.chisw.githubuserssearch.presentation.screens.base.activity

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.chisw.githubuserssearch.domain.exception.Failure

abstract class BaseActivity(@LayoutRes layoutId: Int) : AppCompatActivity(layoutId) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    abstract fun initViews()

    fun showToast(message: Failure<*>) =
        Toast.makeText(this, message.toString(), Toast.LENGTH_LONG).show()


}