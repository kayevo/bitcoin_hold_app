package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.repository.RegisterRepository
import com.kayevo.bitcoinhold.data.result.RegisterRepoResult
import com.kayevo.bitcoinhold.model.Credential
import com.kayevo.bitcoinhold.ui.result.RegisterResult
import kotlinx.coroutines.launch

class RegisterViewModel(
    private val repository: RegisterRepository
) : ViewModel() {
    private val _registerResult = MutableLiveData<RegisterResult>()
    val registerResult: LiveData<RegisterResult> get() = _registerResult

    fun register(email: String, password: String) {
        viewModelScope.launch {
            val credentials = Credential(email, password)
            when(repository.register(credentials)){
                is RegisterRepoResult.Success ->{
                    _registerResult.postValue(RegisterResult.Success)
                }
                else->{
                    _registerResult.postValue(RegisterResult.Error)
                }
            }
        }
    }
}