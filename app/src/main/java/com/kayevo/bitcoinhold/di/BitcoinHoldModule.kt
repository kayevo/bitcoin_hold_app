package com.kayevo.bitcoinhold.di

import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.repository.LoginRepository
import com.kayevo.bitcoinhold.data.repository.MockLoginRepository
import com.kayevo.bitcoinhold.data.service.LoginService
import com.kayevo.bitcoinhold.ui.viewmodel.LoginViewModel
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
    single<LoginService> {
        get<Retrofit>().create(LoginService::class.java)
    }
}

val repositoryModule = module {
    single<LoginRepository> {
        MockLoginRepository(get<LoginService>())
    }
}

val viewModelModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel(get<LoginRepository>())
    }
}