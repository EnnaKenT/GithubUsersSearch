package com.chisw.githubuserssearch.presentation.application

import android.app.Application
import com.chisw.githubuserssearch.BuildConfig
import com.chisw.githubuserssearch.data.network.service.GitHubService
import com.chisw.githubuserssearch.data.repository.GitHubRepositoryImpl
import com.chisw.githubuserssearch.domain.repository.GitHubRepository
import com.chisw.githubuserssearch.domain.usecase.GetUsersByLoginUseCase
import com.chisw.githubuserssearch.presentation.viewModel.UsersViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

    private val retrofit: Retrofit by lazy { provideRetrofit() }

    private val appModule = module {
        single<GitHubService> { GitHubService.GitHubServiceImpl(retrofit) }
        single<GitHubRepository> { GitHubRepositoryImpl(get()) }
        single { GetUsersByLoginUseCase(get()) }

        viewModel {
            UsersViewModel(get())
        }
    }

    override fun onCreate() {
        super.onCreate()

        init()
    }

    private fun init() {
        startKoin()
    }

    private fun startKoin() {
        startKoin {
            // Android context
            androidContext(this@App)
            // modules
            modules(appModule)
        }
    }

    private fun provideRetrofit() = Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}