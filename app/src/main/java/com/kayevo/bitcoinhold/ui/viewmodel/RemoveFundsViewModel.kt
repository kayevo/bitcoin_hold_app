package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.helper.parseBitcoinToSatoshi
import com.kayevo.bitcoinhold.data.helper.parseCurrencyToDouble
import com.kayevo.bitcoinhold.data.repository.RemoveFundsRepository
import com.kayevo.bitcoinhold.data.result.AddFundsRepoResult
import com.kayevo.bitcoinhold.data.result.RemoveFundsRepoResult
import com.kayevo.bitcoinhold.ui.result.AddFundsResult
import com.kayevo.bitcoinhold.ui.result.RemoveFundsResult
import kotlinx.coroutines.launch

class RemoveFundsViewModel(
    private val repository: RemoveFundsRepository
) : ViewModel() {
    private val _removeFundsResult = MutableLiveData<RemoveFundsResult>()
    val removeFundsResult: LiveData<RemoveFundsResult> get() = _removeFundsResult

    fun removeFunds(userId: String, bitcoinAmount: String) {
        viewModelScope.launch {
            when (repository.removeFunds(
                userId,
                bitcoinAmount.parseBitcoinToSatoshi()
            )) {
                is RemoveFundsRepoResult.Success -> {
                    _removeFundsResult.postValue(RemoveFundsResult.Success)
                }
                is RemoveFundsRepoResult.InsufficientFunds -> {
                    _removeFundsResult.postValue(RemoveFundsResult.InsufficientFunds)
                }
                else -> {
                    _removeFundsResult.postValue(RemoveFundsResult.Error)
                }
            }
        }
    }

    fun isValidForm(bitcoinAmount: String): Boolean {
        val bitcoinAmountNum = bitcoinAmount.parseCurrencyToDouble()
        return bitcoinAmount.isNotEmpty() && bitcoinAmountNum != 0.0
    }
}