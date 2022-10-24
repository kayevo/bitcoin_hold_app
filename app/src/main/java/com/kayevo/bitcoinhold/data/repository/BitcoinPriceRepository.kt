package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.BitcoinPriceRepoResult
import com.kayevo.bitcoinhold.data.result.LoginRepoResult
import com.kayevo.bitcoinhold.model.Credential

interface BitcoinPriceRepository {
    suspend fun getBitcoinPrice(): BitcoinPriceRepoResult
}