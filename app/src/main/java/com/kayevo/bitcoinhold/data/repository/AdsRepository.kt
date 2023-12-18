package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.AddAmountRepoResult
import com.kayevo.bitcoinhold.data.result.AdsRepoResult
import retrofit2.http.Query

interface AdsRepository {
    suspend fun getAllAds(apiKey: String): AdsRepoResult
    suspend fun getAds(apiKey: String, email: String): AdsRepoResult
}