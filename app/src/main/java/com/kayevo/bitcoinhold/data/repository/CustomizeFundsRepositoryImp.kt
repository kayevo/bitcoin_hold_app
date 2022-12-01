package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.BuildConfig
import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.AddFundsRepoResult
import com.kayevo.bitcoinhold.data.result.CustomizeFundsRepoResult
import com.kayevo.bitcoinhold.data.service.PortfolioService
import java.nio.channels.spi.AbstractSelectionKey

class CustomizeFundsRepositoryImp(
    private val portfolioService: PortfolioService
) : CustomizeFundsRepository {
    override suspend fun customizeFunds(
        apiKey: String,
        userId: String,
        satoshiAmount: Long,
        bitcoinAveragePrice: Double
    ): CustomizeFundsRepoResult {
        return try {
            val customizeFundsResponse = portfolioService.customizeFunds(
                apiKey = apiKey,
                userId,
                satoshiAmount,
                bitcoinAveragePrice
            )
            when (val responseCode = customizeFundsResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    CustomizeFundsRepoResult.Success
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    CustomizeFundsRepoResult.ErrorServer
                }
                else -> {
                    CustomizeFundsRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            CustomizeFundsRepoResult.ErrorServer
        }
    }
}