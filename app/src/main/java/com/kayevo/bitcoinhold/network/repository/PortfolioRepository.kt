package com.kayevo.bitcoinhold.network.repository

import com.kayevo.bitcoinhold.network.result.AddAmountRepoResult
import com.kayevo.bitcoinhold.network.result.CustomizeAmountRepoResult
import com.kayevo.bitcoinhold.network.result.PortfolioRepoResult
import com.kayevo.bitcoinhold.network.result.RemoveAmountRepoResult

interface PortfolioRepository {
    suspend fun getPortfolio(apiKey: String, userId: String): PortfolioRepoResult

    suspend fun addAmount(
        apiKey: String, userId: String, amount: Long, paidValue: Double
    ): AddAmountRepoResult

    suspend fun removeAmount(
        apiKey: String, userId: String, amount: Long
    ): RemoveAmountRepoResult

    suspend fun setPortfolio(
        apiKey: String, userId: String, amount: Long, paidValue: Double
    ): CustomizeAmountRepoResult
}