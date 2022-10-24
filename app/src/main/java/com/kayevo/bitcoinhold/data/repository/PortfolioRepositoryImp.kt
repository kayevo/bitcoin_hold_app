package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.helper.HttpStatusCodeHelper
import com.kayevo.bitcoinhold.data.result.PortfolioRepoResult
import com.kayevo.bitcoinhold.data.service.PortfolioService

class PortfolioRepositoryImp(
    private val portfolioService: PortfolioService
) : PortfolioRepository {

    override suspend fun getPortfolio(userId: String): PortfolioRepoResult {
        return try {
            val portfolioResponse = portfolioService.getPortfolio(userId)

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
}