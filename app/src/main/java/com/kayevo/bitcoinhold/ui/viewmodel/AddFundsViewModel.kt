package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import com.kayevo.bitcoinhold.data.helper.parseCurrencyToDouble
import com.kayevo.bitcoinhold.data.repository.AddFundsRepository
import com.kayevo.bitcoinhold.data.result.AddFundsRepoResult
import com.kayevo.bitcoinhold.model.Portfolio
import com.kayevo.bitcoinhold.ui.result.AddFundsResult
import kotlinx.coroutines.launch

class AddFundsViewModel(
    private val repository: AddFundsRepository
) : ViewModel() {
    private val _addFundsResult = MutableLiveData<AddFundsResult>()
    val addFundsResult: LiveData<AddFundsResult> get() = _addFundsResult

    fun removeFunds(userId: String, bitcoinAmount: String, bitcoinAveragePrice: String) {
        viewModelScope.launch {
            val portfolio = PortfolioEntity(
                Portfolio(bitcoinAmount, bitcoinAveragePrice)
            )

            when (repository.addFunds(
                userId,
                portfolio.satoshiAmount,
                portfolio.bitcoinAveragePrice
            )) {
                is AddFundsRepoResult.Success -> {
                    _addFundsResult.postValue(AddFundsResult.Success)
                }
                else -> {
                    _addFundsResult.postValue(AddFundsResult.Error)
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