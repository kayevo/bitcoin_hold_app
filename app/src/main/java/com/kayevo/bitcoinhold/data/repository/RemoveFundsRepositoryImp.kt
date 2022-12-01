package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.RemoveFundsRepoResult
import com.kayevo.bitcoinhold.data.service.PortfolioService

class RemoveFundsRepositoryImp(
    private val portfolioService: PortfolioService
) : RemoveFundsRepository {
    override suspend fun removeFunds(
        apiKey: String,
        userId: String,
        satoshiAmount: Long
    ): RemoveFundsRepoResult {
        return try {
            val removeFundsResponse = portfolioService.removeFunds(
                apiKey = apiKey, userId, satoshiAmount
            )

            when (val responseCode = removeFundsResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    RemoveFundsRepoResult.Success
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    RemoveFundsRepoResult.ErrorServer
                }
                HttpStatusCodeHelper.Unauthorized.code -> {
                    RemoveFundsRepoResult.InsufficientFunds
                }
                else -> {
                    RemoveFundsRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            RemoveFundsRepoResult.ErrorServer
        }
    }
}