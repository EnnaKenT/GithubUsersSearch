package com.chisw.githubuserssearch.application

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
        provideRetrofitModule(),
        provideApiModule(),
        provideServiceModule(),
        provideRepositoryModule(),
        provideUseCaseModule(),
        provideViewModelModule()
    )

    private fun provideRetrofitModule() = module {
        fun provideGson(): Gson = GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
            .create()

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

    private fun provideApiModule() = module {
        fun provideGitHubApi(retrofit: Retrofit): GitHubApi = retrofit.create(GitHubApi::class.java)
        single { provideGitHubApi(get()) }
    }

    private fun provideServiceModule() = module {
        single<GitHubService> { GitHubService.GitHubServiceImpl(get()) }
    }

    private fun provideRepositoryModule() = module {
        single<GitHubRepository> { GitHubRepositoryImpl(get()) }
    }

    private fun provideUseCaseModule() = module {
        single { GetUsersByLoginUseCase(get()) }
        single { GetUserReposUseCase(get()) }
        single { GetUserFollowersUseCase(get()) }
    }

    private fun provideViewModelModule() = module {
        customViewModel<UserSearchViewModel> { UserSearchViewModelImpl(get()) }
        customViewModel<UserDetailedViewModel> { UserDetailedViewModelImpl(get(), get()) }
    }

}