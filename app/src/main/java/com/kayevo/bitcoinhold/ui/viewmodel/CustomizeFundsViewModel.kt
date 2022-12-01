package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import com.kayevo.bitcoinhold.data.repository.CustomizeFundsRepository
import com.kayevo.bitcoinhold.data.result.CustomizeFundsRepoResult
import com.kayevo.bitcoinhold.helper.parseCurrencyToDouble
import com.kayevo.bitcoinhold.model.Portfolio
import com.kayevo.bitcoinhold.ui.result.CustomizeFundsResult
import kotlinx.coroutines.launch

class CustomizeFundsViewModel(
    private val repository: CustomizeFundsRepository
) : ViewModel() {
    private val _customizeFundsResult = MutableLiveData<CustomizeFundsResult>()
    val customizeFundsResult: LiveData<CustomizeFundsResult> get() = _customizeFundsResult

    fun customizeFunds(
        apiKey: String,
        userId: String,
        bitcoinAmount: String,
        bitcoinAveragePrice: String
    ) {
        viewModelScope.launch {
            val portfolio = PortfolioEntity(
                Portfolio(bitcoinAmount, bitcoinAveragePrice)
            )

            when (repository.customizeFunds(
                apiKey = apiKey,
                userId,
                portfolio.satoshiAmount,
                portfolio.bitcoinAveragePrice
            )) {
                is CustomizeFundsRepoResult.Success -> {
                    _customizeFundsResult.postValue(CustomizeFundsResult.Success)
                }
                else -> {
                    _customizeFundsResult.postValue(CustomizeFundsResult.Error)
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