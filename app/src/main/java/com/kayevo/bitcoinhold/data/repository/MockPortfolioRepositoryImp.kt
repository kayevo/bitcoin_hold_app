package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.entity.PortfolioEntity
import com.kayevo.bitcoinhold.data.result.PortfolioRepoResult
import com.kayevo.bitcoinhold.data.service.PortfolioService

class MockPortfolioRepositoryImp(
    private val portfolioService: PortfolioService
) : PortfolioRepository {

    override suspend fun getPortfolio(userId: String): PortfolioRepoResult {
        return PortfolioRepoResult.Success(
            PortfolioEntity(
                10000,
                100000.0
            )
        )
    }
}