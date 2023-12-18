package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.repository.PortfolioRepository
import com.kayevo.bitcoinhold.data.result.CustomizeAmountRepoResult
import com.kayevo.bitcoinhold.helper.parseBitcoinToSatoshi
import com.kayevo.bitcoinhold.helper.parseCurrencyToDouble
import com.kayevo.bitcoinhold.ui.result.CustomizeAmountResult
import kotlinx.coroutines.launch

class CustomizeAmountViewModel(
    private val repository: PortfolioRepository
) : ViewModel() {
    private val _customizeAmountResult = MutableLiveData<CustomizeAmountResult>()
    val customizeAmountResult: LiveData<CustomizeAmountResult> get() = _customizeAmountResult

    fun customizeAmount(
        apiKey: String,
        userId: String,
        amount: String,
        paidValue: String
    ) {
        viewModelScope.launch {
            when (repository.setPortfolio(
                apiKey = apiKey,
                userId,
                amount.parseBitcoinToSatoshi(),
                paidValue.parseCurrencyToDouble()
            )) {
                is CustomizeAmountRepoResult.Success -> {
                    _customizeAmountResult.postValue(CustomizeAmountResult.Success)
                }
                else -> {
                    _customizeAmountResult.postValue(CustomizeAmountResult.Error)
                }
            }
        }
    }

    fun isValidForm(bitcoinAmount: String, price: String): Boolean {
        val bitcoinAmountNum = bitcoinAmount.parseCurrencyToDouble()
        val priceNum = price.parseCurrencyToDouble()
        return (bitcoinAmount.isNotEmpty() && price.isNotEmpty())
                && !(bitcoinAmountNum == 0.0 && priceNum != 0.0)
    }
}