package com.kayevo.bitcoinhold.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kayevo.bitcoinhold.data.repository.AnalysisRepository
import com.kayevo.bitcoinhold.data.repository.PortfolioRepository
import com.kayevo.bitcoinhold.data.result.AnalysisRepoResult
import com.kayevo.bitcoinhold.data.result.PortfolioRepoResult
import com.kayevo.bitcoinhold.ui.result.AnalysisResult
import com.kayevo.bitcoinhold.ui.result.PortfolioResult
import kotlinx.coroutines.launch

class PortfolioViewModel(
    private val portfolioRepository: PortfolioRepository,
    private val analysisRepository: AnalysisRepository
) : ViewModel() {
    private val _portfolioResult = MutableLiveData<PortfolioResult>()
    val portfolioResult: LiveData<PortfolioResult> get() = _portfolioResult

    private val _analysisResult = MutableLiveData<AnalysisResult>()
    val analysisResult: LiveData<AnalysisResult> get() = _analysisResult

    fun getPortfolio(apiKey: String, userId: String) {
        viewModelScope.launch {
            when (val portfolioResponse =
                portfolioRepository.getPortfolio(apiKey = apiKey, userId)) {
                is PortfolioRepoResult.Success -> {
                    _portfolioResult.postValue(
                        PortfolioResult.Success(portfolioResponse.portfolio)
                    )
                }

                else -> {
                    _portfolioResult.postValue(PortfolioResult.ErrorServer)
                }
            }
        }
    }

    fun getAnalysis(apiKey: String, userId: String) {
        viewModelScope.launch {
            when (val bitcoinPriceResponse = analysisRepository.getAnalysis(apiKey, userId)) {
                is AnalysisRepoResult.Success -> {
                    _analysisResult.postValue(
                        AnalysisResult.Success(bitcoinPriceResponse.analysisEntity)
                    )
                }
                else -> {
                    _analysisResult.postValue(AnalysisResult.ErrorServer)
                }
            }
        }
    }
}