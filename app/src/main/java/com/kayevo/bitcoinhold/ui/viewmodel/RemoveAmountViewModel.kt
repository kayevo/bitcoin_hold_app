package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.network.repository.PortfolioRepository
import com.kayevo.bitcoinhold.network.result.RemoveAmountRepoResult
import com.kayevo.bitcoinhold.helper.parseBitcoinToSatoshi
import com.kayevo.bitcoinhold.helper.parseCurrencyToDouble
import com.kayevo.bitcoinhold.ui.result.RemoveAmountResult
import kotlinx.coroutines.launch

class RemoveAmountViewModel(
    private val repository: PortfolioRepository
) : ViewModel() {
    private val _removeAmountResult = MutableLiveData<RemoveAmountResult>()
    val removeAmountResult: LiveData<RemoveAmountResult> get() = _removeAmountResult

    fun removeAmount(apiKey: String, userId: String, amount: String) {
        viewModelScope.launch {
            when (repository.removeAmount(
                apiKey = apiKey,
                userId,
                amount.parseBitcoinToSatoshi()
            )) {
                is RemoveAmountRepoResult.Success -> {
                    _removeAmountResult.postValue(RemoveAmountResult.Success)
                }
                is RemoveAmountRepoResult.InsufficientAmount -> {
                    _removeAmountResult.postValue(RemoveAmountResult.InsufficientAmount)
                }
                else -> {
                    _removeAmountResult.postValue(RemoveAmountResult.Error)
                }
            }
        }
    }

    fun isValidForm(bitcoinAmount: String): Boolean {
        val bitcoinAmountNum = bitcoinAmount.parseCurrencyToDouble()
        return bitcoinAmount.isNotEmpty() && bitcoinAmountNum != 0.0
    }
}