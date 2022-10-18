package com.kayevo.bitcoinhold.di

import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.repository.*
import com.kayevo.bitcoinhold.data.service.PortfolioService
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.ui.viewmodel.LoginViewModel
import com.kayevo.bitcoinhold.ui.viewmodel.PortfolioViewModel
import com.kayevo.bitcoinhold.ui.viewmodel.RegisterViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single<Retrofit> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(60, TimeUnit.SECONDS)
            .build()

        Retrofit.Builder()
            .baseUrl(BuildConfig.BITCOIN_HOLD_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val serviceModule = module {
    single<UserService> {
        get<Retrofit>().create(UserService::class.java) as UserService
    }
    single<PortfolioService> {
        get<Retrofit>().create(PortfolioService::class.java) as PortfolioService
    }
}

val repositoryModule = module {
    single<LoginRepository> {
        LoginRepositoryImp(get<UserService>()) as LoginRepository
    }
    single<RegisterRepository> {
        RegisterRepositoryImp(get<UserService>()) as RegisterRepository
    }
    single<PortfolioRepository> {
        MockPortfolioRepositoryImp(get<PortfolioService>()) as PortfolioRepository
    }
}

val viewModelModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel(get<LoginRepository>()) as LoginViewModel
    }
    viewModel<RegisterViewModel> {
        RegisterViewModel(get<RegisterRepository>()) as RegisterViewModel
    }

    viewModel<PortfolioViewModel> {
        PortfolioViewModel(get<PortfolioRepository>()) as PortfolioViewModel
    }
}