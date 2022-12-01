package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import com.kayevo.bitcoinhold.data.repository.BitcoinPriceRepository
import com.kayevo.bitcoinhold.data.repository.PortfolioRepository
import com.kayevo.bitcoinhold.data.result.BitcoinPriceRepoResult
import com.kayevo.bitcoinhold.data.result.PortfolioRepoResult
import com.kayevo.bitcoinhold.model.Portfolio
import com.kayevo.bitcoinhold.model.PortfolioAnalysis
import com.kayevo.bitcoinhold.ui.result.PortfolioAnalysisResult
import com.kayevo.bitcoinhold.ui.result.PortfolioResult
import kotlinx.coroutines.launch

class PortfolioViewModel(
    private val portfolioRepository: PortfolioRepository,
    private val bitcoinPriceRepository: BitcoinPriceRepository
) : ViewModel() {
    private val _portfolioResult = MutableLiveData<PortfolioResult>()
    val portfolioResult: LiveData<PortfolioResult> get() = _portfolioResult

    private val _portfolioAnalysisResult = MutableLiveData<PortfolioAnalysisResult>()
    val portfolioAnalysisResult: LiveData<PortfolioAnalysisResult> get() = _portfolioAnalysisResult

    fun getPortfolio(apiKey: String, userId: String) {
        viewModelScope.launch {
            when (val portfolioResponse =
                portfolioRepository.getPortfolio(apiKey = apiKey, userId)) {
                is PortfolioRepoResult.Success -> {
                    _portfolioResult.postValue(
                        PortfolioResult.Success(Portfolio(portfolioResponse.portfolio))
                    )
                }
                else -> {
                    _portfolioResult.postValue(PortfolioResult.ErrorServer)
                }
            }
        }
    }

    fun getPortfolioAnalysis(apiKey: String, portfolio: Portfolio) {
        viewModelScope.launch {
            when (val bitcoinPriceResponse = bitcoinPriceRepository.getBitcoinPrice(apiKey)) {
                is BitcoinPriceRepoResult.Success -> {
                    val portfolioEntity = PortfolioEntity(portfolio)
                    val portfolioAnalysis = PortfolioAnalysis(
                        bitcoinPriceResponse.bitcoinPriceEntity.priceInBRL,
                        portfolioEntity
                    )
                    _portfolioAnalysisResult.postValue(
                        PortfolioAnalysisResult.Success(portfolioAnalysis)
                    )
                }
                else -> {
                    _portfolioAnalysisResult.postValue(PortfolioAnalysisResult.ErrorServer)
                }
            }
        }
    }
}