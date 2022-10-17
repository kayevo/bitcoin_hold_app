package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.repository.LoginRepository
import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.model.Credential
import com.kayevo.bitcoinhold.ui.result.LoginResult
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            val credentials = Credential(email, password)
            when(val loginResponse = repository.login(credentials)){
                is LoginRepoResult.Success ->{
                    _loginResult.postValue(LoginResult.Success(loginResponse.userId))
                }
                is LoginRepoResult.NotFound ->{
                    _loginResult.postValue(LoginResult.NotFound)
                }
                else->{
                    _loginResult.postValue(LoginResult.Error)
                }
            }
        }
    }
}