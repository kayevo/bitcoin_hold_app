package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kayevo.bitcoinhold.ui.result.LoginViewResult

class LoginViewModel(): ViewModel() {
    private val _loginResult = MutableLiveData<LoginViewResult>()
    val loginResult: LiveData<LoginViewResult> get() = _loginResult
}