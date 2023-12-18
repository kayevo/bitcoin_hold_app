package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.helper.parseCurrencyToDouble
import com.kayevo.bitcoinhold.data.repository.PortfolioRepository
import com.kayevo.bitcoinhold.data.result.AddAmountRepoResult
import com.kayevo.bitcoinhold.helper.parseBitcoinToSatoshi
import com.kayevo.bitcoinhold.ui.result.AddAmountResult
import kotlinx.coroutines.launch

class AddAmountViewModel(
    private val repository: PortfolioRepository
) : ViewModel() {
    private val _addAmountResult = MutableLiveData<AddAmountResult>()
    val addAmountResult: LiveData<AddAmountResult> get() = _addAmountResult

    fun addAmount(apiKey: String, userId: String, amount: String, paidValue: String) {
        viewModelScope.launch {
            when (repository.addAmount(
                apiKey = apiKey,
                userId,
                amount.parseBitcoinToSatoshi(),
                paidValue.parseCurrencyToDouble()
            )) {
                is AddAmountRepoResult.Success -> {
                    _addAmountResult.postValue(AddAmountResult.Success)
                }
                else -> {
                    _addAmountResult.postValue(AddAmountResult.Error)
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