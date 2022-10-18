package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.PortfolioRepoResult

interface PortfolioRepository {
    suspend fun getPortfolio(userId: String): PortfolioRepoResult
}