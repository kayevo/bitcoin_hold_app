package com.kayevo.bitcoinhold.di

import com.kayevo.bitcoinhold.ui.viewmodel.LoginViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel<LoginViewModel> {
        LoginViewModel()
    }
}