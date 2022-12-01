package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.AddFundsRepoResult
import com.kayevo.bitcoinhold.data.result.CustomizeFundsRepoResult

interface CustomizeFundsRepository {
    suspend fun customizeFunds(
        apiKey: String, userId: String, satoshiAmount: Long, bitcoinAveragePrice: Double
    ): CustomizeFundsRepoResult
}