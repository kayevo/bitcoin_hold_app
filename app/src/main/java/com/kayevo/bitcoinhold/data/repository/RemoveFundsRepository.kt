package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.AddFundsRepoResult
import com.kayevo.bitcoinhold.data.result.RemoveFundsRepoResult

interface RemoveFundsRepository {
    suspend fun removeFunds(
        userId: String, satoshiAmount: Long
    ): RemoveFundsRepoResult
}