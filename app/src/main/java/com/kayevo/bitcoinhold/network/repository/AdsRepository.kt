package com.kayevo.bitcoinhold.network.repository

import com.kayevo.bitcoinhold.network.result.AddAmountRepoResult
import com.kayevo.bitcoinhold.network.result.AdsRepoResult
import retrofit2.http.Query

interface AdsRepository {
    suspend fun getAllAds(apiKey: String): AdsRepoResult
    suspend fun getAds(apiKey: String, email: String): AdsRepoResult
}