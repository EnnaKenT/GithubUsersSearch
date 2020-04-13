package com.chisw.githubuserssearch.presentation.application

import android.app.Application
import com.chisw.githubuserssearch.BuildConfig
import com.chisw.githubuserssearch.data.network.api.GitHubApi
import com.chisw.githubuserssearch.data.network.service.GitHubService
import com.chisw.githubuserssearch.data.repository.GitHubRepositoryImpl
import com.chisw.githubuserssearch.domain.repository.GitHubRepository
import com.chisw.githubuserssearch.domain.usecase.GetUserFollowersUseCase
import com.chisw.githubuserssearch.domain.usecase.GetUserReposUseCase
import com.chisw.githubuserssearch.domain.usecase.GetUsersByLoginUseCase
import com.chisw.githubuserssearch.presentation.utils.customViewModel
import com.chisw.githubuserssearch.presentation.viewModel.userDetailed.UserDetailedViewModel
import com.chisw.githubuserssearch.presentation.viewModel.userDetailed.UserDetailedViewModelImpl
import com.chisw.githubuserssearch.presentation.viewModel.userSearch.UserSearchViewModel
import com.chisw.githubuserssearch.presentation.viewModel.userSearch.UserSearchViewModelImpl
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {

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
            modules(
                listOf(
                    retrofitModule,
                    apiModule,
                    serviceModule,
                    repositoryModule,
                    useCaseModule,
                    viewModelModule
                )
            )
        }
    }

    private val retrofitModule = module {
        fun provideGson(): Gson =
            GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()

        fun provideHttpClient(): OkHttpClient = OkHttpClient.Builder().build()
        fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit =
            Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(factory))
                .client(client)
                .build()

        single { provideGson() }
        single { provideHttpClient() }
        single { provideRetrofit(get(), get()) }
    }
    private val apiModule = module {
        fun provideGitHubApi(retrofit: Retrofit): GitHubApi = retrofit.create(GitHubApi::class.java)
        single { provideGitHubApi(get()) }
    }
    private val serviceModule = module {
        single<GitHubService> { GitHubService.GitHubServiceImpl(get()) }
    }
    private val repositoryModule = module {
        single<GitHubRepository> { GitHubRepositoryImpl(get()) }
    }
    private val useCaseModule = module {
        single { GetUsersByLoginUseCase(get()) }
        single { GetUserReposUseCase(get()) }
        single { GetUserFollowersUseCase(get()) }
    }
    private val viewModelModule = module {
        customViewModel<UserSearchViewModel> { UserSearchViewModelImpl(get()) }
        customViewModel<UserDetailedViewModel> { UserDetailedViewModelImpl(get(), get()) }
    }

}