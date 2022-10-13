package com.kayevo.bitcoinhold.application

import android.app.Application
import com.kayevo.bitcoinhold.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BitcoinHoldApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BitcoinHoldApplication)
            modules(
                viewModelModule
                )
        }
    }
}