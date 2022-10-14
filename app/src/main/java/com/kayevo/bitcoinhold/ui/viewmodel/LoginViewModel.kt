package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.repository.LoginRepository
import com.kayevo.bitcoinhold.data.repository.MockLoginRepository
import com.kayevo.bitcoinhold.data.result.LoginRepositoryResult
import com.kayevo.bitcoinhold.model.result.LoginResult
import kotlinx.coroutines.launch

class LoginViewModel(
    private val repository: LoginRepository
) : ViewModel() {
    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> get() = _loginResult

    fun login(email: String, password: String) {
        viewModelScope.launch {
            when(val loginResult = repository.login(email, password)){
                is LoginRepositoryResult.Success ->{
                    _loginResult.postValue(LoginResult.Success(loginResult.user))
                }
                is LoginRepositoryResult.NotFound ->{
                    _loginResult.postValue(LoginResult.NotFound)
                }
                else->{
                    _loginResult.postValue(LoginResult.Error)
                }
            }
        }
    }
}