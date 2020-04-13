package com.chisw.githubuserssearch.presentation.utils

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import org.koin.androidx.viewmodel.dsl.setIsViewModel
import org.koin.androidx.viewmodel.ext.android.getViewModel
import org.koin.core.definition.BeanDefinition
import org.koin.core.definition.Definition
import org.koin.core.module.Module
import org.koin.core.parameter.ParametersDefinition
import org.koin.core.qualifier.Qualifier
import org.koin.core.qualifier.named

inline fun <reified T> Module.customViewModel(
    qualifier: Qualifier? = null,
    noinline definition: Definition<T>
): BeanDefinition<T> = viewModel(qualifier ?: named(T::class.java.name), definition = definition)

inline fun <reified T> Module.viewModel(
    qualifier: Qualifier? = null,
    override: Boolean = false,
    noinline definition: Definition<T>
): BeanDefinition<T> {
    val beanDefinition = factory(qualifier, override, definition)
    beanDefinition.setIsViewModel()
    return beanDefinition
}

inline fun <reified T> LifecycleOwner.customViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): Lazy<T> =
    lazy { getViewModel<ViewModel>(qualifier ?: named(T::class.java.name), parameters) as T }

inline fun <reified T> LifecycleOwner.getCustomViewModel(
    qualifier: Qualifier? = null,
    noinline parameters: ParametersDefinition? = null
): T = getViewModel<ViewModel>(qualifier ?: named(T::class.java.name), parameters) as T