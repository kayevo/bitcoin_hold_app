package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.repository.PortfolioRepository
import com.kayevo.bitcoinhold.data.result.PortfolioRepoResult
import com.kayevo.bitcoinhold.model.Portfolio
import com.kayevo.bitcoinhold.ui.result.PortfolioResult
import kotlinx.coroutines.launch

class PortfolioViewModel(
    private val repository: PortfolioRepository
) : ViewModel() {
    private val _portfolioRepoResult = MutableLiveData<PortfolioResult>()
    val portfolioRepoResult: LiveData<PortfolioResult> get() = _portfolioRepoResult

    fun getPortfolio(userId: String) {
        viewModelScope.launch {
            when (val portfolioResponse = repository.getPortfolio(userId)) {
                is PortfolioRepoResult.Success -> {
                    _portfolioRepoResult.postValue(
                        PortfolioResult.Success(Portfolio(portfolioResponse.portfolio))
                    )
                }
                else -> {
                    _portfolioRepoResult.postValue(PortfolioResult.ErrorServer)
                }
            }
        }
    }
}