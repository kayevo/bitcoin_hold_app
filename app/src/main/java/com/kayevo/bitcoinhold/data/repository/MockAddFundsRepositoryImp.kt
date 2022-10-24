package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.AddFundsRepoResult
import com.kayevo.bitcoinhold.data.service.PortfolioService

class MockAddFundsRepositoryImp(
    private val portfolioService: PortfolioService
) : AddFundsRepository {
    override suspend fun addFunds(
        userId: String,
        satoshiAmount: Long,
        bitcoinAveragePrice: Double
    ): AddFundsRepoResult {
        return AddFundsRepoResult.Success
    }
}