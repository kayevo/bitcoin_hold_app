package com.kayevo.bitcoinhold.di

import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.repository.*
import com.kayevo.bitcoinhold.data.service.AdsService
import com.kayevo.bitcoinhold.data.service.AnalysisService
import com.kayevo.bitcoinhold.data.service.PortfolioService
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.ui.viewmodel.*
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
    single<AnalysisService> {
        get<Retrofit>().create(AnalysisService::class.java) as AnalysisService
    }
    single<AdsService> {
        get<Retrofit>().create(AdsService::class.java) as AdsService
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
        PortfolioRepositoryImp(get<PortfolioService>()) as PortfolioRepository
    }
    single<AnalysisRepository> {
        AnalysisRepositoryImp(get<AnalysisService>()) as AnalysisRepository
    }
    single<AdsRepository> {
        AdsRepositoryImp(get<AdsService>()) as AdsRepository
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
        PortfolioViewModel(
            get<PortfolioRepository>(),
            get<AnalysisRepository>()
        ) as PortfolioViewModel
    }
    viewModel<AddAmountViewModel> {
        AddAmountViewModel(get<PortfolioRepository>()) as AddAmountViewModel
    }
    viewModel<RemoveAmountViewModel> {
        RemoveAmountViewModel(get<PortfolioRepository>()) as RemoveAmountViewModel
    }
    viewModel<CustomizeAmountViewModel> {
        CustomizeAmountViewModel(get<PortfolioRepository>()) as CustomizeAmountViewModel
    }
    viewModel<AdsViewModel> {
        AdsViewModel(get<AdsRepository>()) as AdsViewModel
    }

}