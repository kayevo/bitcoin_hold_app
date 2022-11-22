package com.kayevo.bitcoinhold.data.repository

import com.kayevo.bitcoinhold.data.result.AddFundsRepoResult
import com.kayevo.bitcoinhold.data.result.AdsRepoResult
import retrofit2.http.Query

interface AdsRepository {
    suspend fun getAllAds(): AdsRepoResult
    suspend fun getAds(email: String): AdsRepoResult
}