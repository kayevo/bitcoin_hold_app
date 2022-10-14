package com.kayevo.bitcoinhold.application

import android.app.Application
import com.kayevo.bitcoinhold.di.repositoryModule
import com.kayevo.bitcoinhold.di.retrofitModule
import com.kayevo.bitcoinhold.di.serviceModule
import com.kayevo.bitcoinhold.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BitcoinHoldApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BitcoinHoldApplication)
            modules(
                retrofitModule,
                serviceModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}