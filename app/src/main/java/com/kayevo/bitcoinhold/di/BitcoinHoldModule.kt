package com.kayevo.bitcoinhold.di

import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.repository.*
import com.kayevo.bitcoinhold.data.service.BitcoinPriceService
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
    single<BitcoinPriceService> {
        get<Retrofit>().create(BitcoinPriceService::class.java) as BitcoinPriceService
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
    single<AddFundsRepository> {
        AddFundsRepositoryImp(get<PortfolioService>()) as AddFundsRepository
    }
    single<RemoveFundsRepository> {
        RemoveFundsRepositoryImp(get<PortfolioService>()) as RemoveFundsRepository
    }
    single<BitcoinPriceRepository> {
        BitcoinPriceRepositoryImp(get<BitcoinPriceService>()) as BitcoinPriceRepository
    }
    single<CustomizeFundsRepository> {
        CustomizeFundsRepositoryImp(get<PortfolioService>()) as CustomizeFundsRepository
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
            get<BitcoinPriceRepository>()
        ) as PortfolioViewModel
    }
    viewModel<AddFundsViewModel> {
        AddFundsViewModel(get<AddFundsRepository>()) as AddFundsViewModel
    }
    viewModel<RemoveFundsViewModel> {
        RemoveFundsViewModel(get<RemoveFundsRepository>()) as RemoveFundsViewModel
    }
    viewModel<CustomizeFundsViewModel> {
        CustomizeFundsViewModel(get<CustomizeFundsRepository>()) as CustomizeFundsViewModel
    }
}