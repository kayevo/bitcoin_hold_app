package com.kayevo.bitcoinhold.network.repository

import com.kayevo.bitcoinhold.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.network.result.AddAmountRepoResult
import com.kayevo.bitcoinhold.network.result.CustomizeAmountRepoResult
import com.kayevo.bitcoinhold.network.result.PortfolioRepoResult
import com.kayevo.bitcoinhold.network.result.RemoveAmountRepoResult
import com.kayevo.bitcoinhold.network.service.PortfolioService

class PortfolioRepositoryImp(
    private val portfolioService: PortfolioService
) : PortfolioRepository {

    override suspend fun getPortfolio(apiKey: String, userId: String): PortfolioRepoResult {
        return try {
            val portfolioResponse = portfolioService.getPortfolio(
                apiKey = apiKey,
                userId = userId
            )

            when (val responseCode = portfolioResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    portfolioResponse.body()?.let { portfolio ->
                        PortfolioRepoResult.Success(portfolio)
                    } ?: run {
                        PortfolioRepoResult.ErrorServer
                    }
                }
                HttpStatusCodeHelper.NotFound.code -> {
                    PortfolioRepoResult.NotFound
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    PortfolioRepoResult.ErrorServer
                }
                else -> {
                    PortfolioRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            PortfolioRepoResult.ErrorServer
        }
    }

    override suspend fun removeAmount(
        apiKey: String,
        userId: String,
        amount: Long
    ): RemoveAmountRepoResult {
        return try {
            val removeAmountResponse = portfolioService.removeAmount(
                apiKey = apiKey, userId, amount
            )

            when (val responseCode = removeAmountResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    RemoveAmountRepoResult.Success
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    RemoveAmountRepoResult.ErrorServer
                }
                HttpStatusCodeHelper.Unauthorized.code -> {
                    RemoveAmountRepoResult.InsufficientAmount
                }
                else -> {
                    RemoveAmountRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            RemoveAmountRepoResult.ErrorServer
        }
    }

    override suspend fun addAmount(
        apiKey: String,
        userId: String,
        amount: Long,
        paidValue: Double
    ): AddAmountRepoResult {
        return try {
            val addAmountResponse = portfolioService.addAmount(
                apiKey,
                userId,
                amount,
                paidValue
            )
            when (val responseCode = addAmountResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    AddAmountRepoResult.Success
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    AddAmountRepoResult.ErrorServer
                }
                else -> {
                    AddAmountRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            AddAmountRepoResult.ErrorServer
        }
    }

    override suspend fun setPortfolio(
        apiKey: String,
        userId: String,
        amount: Long,
        paidValue: Double
    ): CustomizeAmountRepoResult {
        return try {
            val customizeAmountResponse = portfolioService.setPortfolio(
                apiKey = apiKey,
                userId,
                amount,
                paidValue
            )
            when (val responseCode = customizeAmountResponse.code()) {
                HttpStatusCodeHelper.OK.code -> {
                    CustomizeAmountRepoResult.Success
                }
                HttpStatusCodeHelper.InternalServerError.code -> {
                    CustomizeAmountRepoResult.ErrorServer
                }
                else -> {
                    CustomizeAmountRepoResult.Error(responseCode)
                }
            }
        } catch (e: Exception) {
            CustomizeAmountRepoResult.ErrorServer
        }
    }
}