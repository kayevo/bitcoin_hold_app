package com.kayevo.bitcoinhold.di

import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.repository.LoginRepository
import com.kayevo.bitcoinhold.data.repository.MockLoginRepositoryImp
import com.kayevo.bitcoinhold.data.repository.MockRegisterRepositoryImp
import com.kayevo.bitcoinhold.data.repository.RegisterRepository
import com.kayevo.bitcoinhold.data.service.UserService
import com.kayevo.bitcoinhold.ui.viewmodel.LoginViewModel
import com.kayevo.bitcoinhold.ui.viewmodel.RegisterViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val retrofitModule = module {
    single<Retrofit> {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        Retrofit.Builder()
            .baseUrl(BuildConfig.BITCOIN_HOLD_BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}

val serviceModule = module {
    single<UserService> {
        get<Retrofit>().create(UserService::class.java)
    }
}

val repositoryModule = module {
    single<LoginRepository> {
        MockLoginRepositoryImp(get<UserService>())
    }
    single<RegisterRepository> {
        MockRegisterRepositoryImp(get<UserService>())
    }
}

val viewModelModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel(get<LoginRepository>())
    }
    viewModel<RegisterViewModel> {
        RegisterViewModel(get<RegisterRepository>())
    }
}