package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.AddFundsRepoResult
import com.kayevo.bitcoinhold.data.service.PortfolioService

class AddFundsRepositoryImp(
    private val portfolioService: PortfolioService
) : AddFundsRepository {
    override suspend fun addFunds(
        apiKey: String,
        userId: String,
        satoshiAmount: Long,
        bitcoinAveragePrice: Double
    ): AddFundsRepoResult {
        return try {
            val addFundsResponse = portfolioService.addFunds(
                apiKey,
                userId,
                satoshiAmount,
                bitcoinAveragePrice
            )
            when (val responseCode = addFundsResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    AddFundsRepoResult.Success
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    AddFundsRepoResult.ErrorServer
                }
                else -> {
                    AddFundsRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            AddFundsRepoResult.ErrorServer
        }
    }
}