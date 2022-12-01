package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.repository.RegisterRepository
import com.kayevo.bitcoinhold.data.result.RegisterRepoResult
import com.kayevo.bitcoinhold.model.Credential
import com.kayevo.bitcoinhold.ui.result.RegisterResult
import com.kayevo.bitcoinhold.ui.result.RegisteredEmailResult
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: RegisterRepository
) : ViewModel() {
    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> get() = _registerResult

    private val _registeredEmailResult = MutableLiveData<RegisteredEmailResult>()
    val registeredEmailResult: LiveData<RegisteredEmailResult> get() = _registeredEmailResult

    fun register(apiKey: String, email: String, password: String) {
        viewModelScope.launch {
            val credentials = Credential(email, password)

            when (repository.register(apiKey = apiKey, credentials)) {
                is RegisterRepoResult.Success -> {
                    _registerResult.postValue(RegisterResult.Success)
                }
                else -> {
                    _registerResult.postValue(RegisterResult.Error)
                }
            }
        }
    }

    fun registeredEmail(apiKey: String, email: String) {
        viewModelScope.launch {
            when (repository.registeredEmail(apiKey = apiKey, email)) {
                is RegisteredEmailResult.NotRegisteredEmail -> {
                    _registeredEmailResult.postValue(RegisteredEmailResult.NotRegisteredEmail)
                }
                is RegisteredEmailResult.RegisteredEmail -> {
                    _registeredEmailResult.postValue(RegisteredEmailResult.RegisteredEmail)
                }
                else -> {
                    _registeredEmailResult.postValue(RegisteredEmailResult.ErrorServer)
                }
            }
        }
    }

    fun isValidForm(email: String, password: String): Boolean {
        return email.isNotEmpty() && password.isNotEmpty()
    }
}