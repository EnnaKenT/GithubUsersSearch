package com.chisw.githubuserssearch.presentation.application

import com.chisw.githubuserssearch.BuildConfig
import com.chisw.githubuserssearch.data.network.api.GitHubApi
import com.chisw.githubuserssearch.data.network.service.GitHubService
import com.chisw.githubuserssearch.data.repository.GitHubRepositoryImpl
import com.chisw.githubuserssearch.domain.repository.GitHubRepository
import com.chisw.githubuserssearch.domain.usecase.GetUserFollowersUseCase
import com.chisw.githubuserssearch.domain.usecase.GetUserReposUseCase
import com.chisw.githubuserssearch.domain.usecase.GetUsersByLoginUseCase
import com.chisw.githubuserssearch.presentation.screen.user_detailed.UserDetailedViewModel
import com.chisw.githubuserssearch.presentation.screen.user_detailed.UserDetailedViewModelImpl
import com.chisw.githubuserssearch.presentation.screen.user_search.UserSearchViewModel
import com.chisw.githubuserssearch.presentation.screen.user_search.UserSearchViewModelImpl
import com.chisw.githubuserssearch.presentation.utils.customViewModel
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KoinModules {

    fun getModules() = listOf(
        retrofitModule,
        apiModule,
        serviceModule,
        repositoryModule,
        useCaseModule,
        viewModelModule
    )

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
        customViewModel<UserSearchViewModel> {
            UserSearchViewModelImpl(get())
        }
        customViewModel<UserDetailedViewModel> {
            UserDetailedViewModelImpl(get(), get())
        }
    }

}