package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.AddFundsRepoResult

interface AddFundsRepository {
    suspend fun addFunds(
        apiKey: String, userId: String, satoshiAmount: Long, bitcoinAveragePrice: Double
    ): AddFundsRepoResult
}